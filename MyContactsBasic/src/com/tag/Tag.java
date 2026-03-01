package com.tag;

import java.util.Objects;

public class Tag {
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName.toLowerCase().trim(); // Basic object manipulation
    }

    public String getTagName() {
        return tagName;
    }

    // Java Concept: Overriding equals() and hashCode() for Set uniqueness
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }

    @Override
    public String toString() {
        return "#" + tagName;
    }
}
