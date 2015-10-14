package com.caelum.geradordeprovas.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import com.caelum.geradordeprovas.models.Tag;

public class TagConverter implements Converter<String, Set<Tag>> {

	@Override
	public Set<Tag> convert(String tags) {
		String[] tagsSeparadas = tags.split(("\\s*,\\s*"));
		List<String> tagsStringList = Arrays.asList(tagsSeparadas);
		Set<Tag> tagsSet = new HashSet<Tag>();
		
		for (String tag : tagsStringList) {
			Tag tagObj = new Tag();
			tagObj.setNome(tag);
			tagsSet.add(tagObj);
		}
		
		return tagsSet;
	}

}