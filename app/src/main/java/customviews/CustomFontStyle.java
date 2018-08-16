package customviews;

public enum CustomFontStyle {
    ROBOTO_BLACK(0, FontProvider.ROBOTO_BLACK),
    ROBOTO_BLACK_ITALIC(1, FontProvider.ROBOTO_BLACK_ITALIC),
    ROBOTO_BOLD(2, FontProvider.ROBOTO_BOLD),
    ROBOTO_BOLD_ITALIC(2, FontProvider.ROBOTO_BOLD_ITALIC),
    ROBOTO_ITALIC(4, FontProvider.ROBOTO_ITALIC),
    ROBOTO_LIGHT(5, FontProvider.ROBOTO_LIGHT),
    ROBOTO_LIGHT_ITALIC(6, FontProvider.ROBOTO_LIGHT_ITALIC),
    ROBOTO_MEDIUM(7, FontProvider.ROBOTO_MEDIUM),
    ROBOTO_MEDIUM_ITALIC(8, FontProvider.ROBOTO_MEDIUM_ITALIC),
    ROBOTO_REGULAR(9, FontProvider.ROBOTO_REGULAR),
    ROBOTO_THIN(10, FontProvider.ROBOTO_THIN),
    ROBOTO_THIN_ITALIC(11, FontProvider.ROBOTO_THIN_ITALIC);


    private final int id;
    private final String fontName;

    CustomFontStyle(int id, String fontName) {
        this.id = id;
        this.fontName = fontName;
    }

    /**
     * @param id
     * @return
     */
    public static CustomFontStyle getCustomFontStyle(int id) {
        for (CustomFontStyle fontStyle : CustomFontStyle.values()) {
            if (fontStyle.id == id) return fontStyle;
        }
        return ROBOTO_BLACK;
    }

    /**
     * @return
     */
    public int getIndex() {
        return id;
    }

    /**
     * @return
     */
    public String getFontName() {
        return fontName;
    }
}