package com.tlv8.v3.system.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlv8.v3.common.base.SecurityCode;
import com.tlv8.v3.common.base.SecurityImage;
import com.tlv8.v3.common.constant.CacheConstants;
import com.tlv8.v3.common.constant.Constants;
import com.tlv8.v3.common.domain.AjaxResult;
import com.tlv8.v3.common.redis.RedisCache;
import com.tlv8.v3.common.utils.Base64;
import com.tlv8.v3.common.utils.IDUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 验证码
 */
@Controller
@RequestMapping("/system/common")
public class CaptchaImage {

	@Autowired
	private RedisCache redisCache;

	@ResponseBody
	@RequestMapping("/captchaimage")
	public Object execute(HttpServletRequest req, HttpServletResponse httpServletResponse) throws Exception {
		AjaxResult ajax = AjaxResult.success();

		// 保存验证码信息
		String uuid = IDUtils.getGUID();
		String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

		// 获取默认难度和长度的验证码
		String securityCode = SecurityCode.getSecurityCode();
		redisCache.setCacheObject(verifyKey, securityCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
		BufferedImage image = SecurityImage.createImage(securityCode);
		// 放入session中
		req.getSession().setAttribute("SESSION_SECURITY_CODE", securityCode);

		FastByteArrayOutputStream os = new FastByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", os);
		} catch (IOException e) {
			return AjaxResult.error(e.getMessage());
		}
		ajax.put("uuid", uuid);
		ajax.put("img", Base64.encode(os.toByteArray()));
		return ajax;
	}

}