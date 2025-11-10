package com.javaweb.project.utils;

import java.text.Normalizer;
import java.util.UUID;
public class SlugUtils {
    public static String toSlug(String title) {
        if (title == null) return null;

        String slug = title.toLowerCase();

        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        slug = slug.replaceAll("[^a-z0-9\\s-+]", "");

        slug = slug.trim().replaceAll("\\s+", "-");

        slug = slug.replaceAll("-{2,}", "-");

        return slug;
    }

    // Hàm tạo slug duy nhất (thêm hậu tố ngẫu nhiên)
    public static String toUniqueSlug(String title) {
        String baseSlug = toSlug(title);
        String randomSuffix = UUID.randomUUID().toString().substring(0, 6);
        return baseSlug + "-" + randomSuffix;
    }
}
