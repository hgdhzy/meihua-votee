/**
 * 
 */
package com.meihua.utils;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meihua.constants.Constant;
import com.meihua.domain.TokenUserInfo;
import com.meihua.domain.VoteObject;
import com.meihua.domain.VoteObjectExample;
import com.meihua.domain.VoteObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author hzy
 *
 */
@Component
public class TokenService {
	// 密钥
	private static final Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(Constant.SECRET),
			SignatureAlgorithm.HS512.getJcaName());
	// public static final Key signingKey = MacProvider.generateKey();

	/**
	 * 被测评对象信息检索
	 */
	@Autowired
	private VoteObjectMapper voteObjectMapperAutowired;

	public VoteObjectMapper getVoteObjectMapperAutowired() {
		return voteObjectMapperAutowired;
	}

	public void setVoteObjectMapperAutowired(VoteObjectMapper voteObjectMapperAutowired) {
		this.voteObjectMapperAutowired = voteObjectMapperAutowired;
	}

	private static VoteObjectMapper voteObjectMapper;

	@SuppressWarnings("static-access")
	@PostConstruct
	public void init() {
		this.voteObjectMapper = voteObjectMapperAutowired;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String createToken(TokenUserInfo tokenUserInfo, long expired) {
		// token作成
		Map userInfo = MapUtils.java2Map(tokenUserInfo);
		JwtBuilder builder = Jwts.builder().setClaims(userInfo).setSubject(tokenUserInfo.getCode())
				.signWith(SignatureAlgorithm.HS512, signingKey);
		if (expired > 0) {
			builder.setExpiration(new Date(expired));
		}
		String token = builder.compact();

		// 数据库token及token_exp_time更新
		VoteObjectExample voteObjectExample = new VoteObjectExample();
		voteObjectExample.createCriteria().andTenantIdEqualTo(tokenUserInfo.getTenantId())
				.andObjectIdEqualTo(tokenUserInfo.getId()).andTypeEqualTo((byte) (int) tokenUserInfo.getTypeId());
		VoteObject voteObject = new VoteObject();
		voteObject.setToken(token);
		voteObject.setTokenExpTime(new Date(expired));
		voteObjectMapper.updateByExampleSelective(voteObject, voteObjectExample);

		return token;
	}

	public static TokenUserInfo validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
		Jwts.parser().requireSubject(claims.get("code").toString()).setSigningKey(signingKey).parseClaimsJws(token);
		TokenUserInfo tokenUserInfo = new TokenUserInfo();
		tokenUserInfo = MapUtils.map2Java(tokenUserInfo, claims);

		VoteObjectExample voteObjectExample = new VoteObjectExample();
		voteObjectExample.or().andTenantIdEqualTo(tokenUserInfo.getTenantId()).andObjectIdEqualTo(tokenUserInfo.getId())
				.andTypeEqualTo((byte) (int) tokenUserInfo.getTypeId());
		List<VoteObject> voteObject = voteObjectMapper.selectByExampleWithBLOBs(voteObjectExample);
		Date dateNow = new Date(System.currentTimeMillis());
		if (voteObject.size() != 1 || !token.equals(voteObject.get(0).getToken())) {
			throw new SignatureException(null);
		}
		if (dateNow.after(voteObject.get(0).getTokenExpTime())) {
			throw new ExpiredJwtException(null, null, null);
		}

		return tokenUserInfo;
	}

	public static void revokeToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		TokenUserInfo tokenUserInfo = validateToken(token);

		VoteObjectExample voteObjectExample = new VoteObjectExample();
		voteObjectExample.createCriteria().andTenantIdEqualTo(tokenUserInfo.getTenantId())
				.andObjectIdEqualTo(tokenUserInfo.getId()).andTypeEqualTo((byte) (int) tokenUserInfo.getTypeId());
		VoteObject voteObject = new VoteObject();
		voteObject.setToken("");
		voteObject.setTokenExpTime(new Date());
		voteObjectMapper.updateByExampleSelective(voteObject, voteObjectExample);
	}
}
