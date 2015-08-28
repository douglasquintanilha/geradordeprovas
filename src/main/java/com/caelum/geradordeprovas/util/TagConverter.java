package com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;
import com.caelum.geradordeprovas.models.Tag;

public class TagConverter implements Converter<String, Tag> {

	@Override
	public Tag convert(String tag) {
		Tag tagObj = new Tag();
		
		tagObj.setNome(tag);

		return tagObj;
	}

}