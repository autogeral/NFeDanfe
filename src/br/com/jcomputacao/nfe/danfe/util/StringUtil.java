package br.com.jcomputacao.nfe.danfe.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author murilo.lima
 */
public class StringUtil {

    private static final Map<Character, String> HTML_ISO_CHARS = new HashMap<Character, String>();
    private static final Map<String, Character> HTML_ISO_ENTITIES = new HashMap<String, Character>();

    static {
        HTML_ISO_CHARS.put('"', "&quot;");
        HTML_ISO_CHARS.put('\'', "&apos;");
//        HTML_ISO_CHARS.put('&', "&amp;");
        HTML_ISO_CHARS.put('<', "&lt;");
        HTML_ISO_CHARS.put('>', "&gt;");
//        HTML_ISO_CHARS.put(' ',"&nbsp;");
        HTML_ISO_CHARS.put('¡', "&iexcl;");
        HTML_ISO_CHARS.put('¢', "&cent;");
        HTML_ISO_CHARS.put('£', "&pound;");
        HTML_ISO_CHARS.put('¤', "&curren;");
        HTML_ISO_CHARS.put('¥', "&yen;");
        HTML_ISO_CHARS.put('¦', "&brvbar;");
        HTML_ISO_CHARS.put('§', "&sect;");
        HTML_ISO_CHARS.put('¨', "&uml;");
        HTML_ISO_CHARS.put('©', "&copy;");
        HTML_ISO_CHARS.put('ª', "&ordf;");
        HTML_ISO_CHARS.put('«', "&laquo;");
        HTML_ISO_CHARS.put('¬', "&not;");
        HTML_ISO_CHARS.put('�', "&shy;");
        HTML_ISO_CHARS.put('®', "&reg;");
        HTML_ISO_CHARS.put('¯', "&macr;");
        HTML_ISO_CHARS.put('°', "&deg;");
        HTML_ISO_CHARS.put('±', "&plusmn;");
        HTML_ISO_CHARS.put('²', "&sup2;");
        HTML_ISO_CHARS.put('³', "&sup3;");
        HTML_ISO_CHARS.put('´', "&acute;");
        HTML_ISO_CHARS.put('µ', "&micro;");
        HTML_ISO_CHARS.put('¶', "&para;");
        HTML_ISO_CHARS.put('·', "&middot;");
        HTML_ISO_CHARS.put('¸', "&cedil;");
        HTML_ISO_CHARS.put('¹', "&sup1;");
        HTML_ISO_CHARS.put('º', "&ordm;");
        HTML_ISO_CHARS.put('»', "&raquo;");
        HTML_ISO_CHARS.put('¼', "&frac14;");
        HTML_ISO_CHARS.put('½', "&frac12;");
        HTML_ISO_CHARS.put('¾', "&frac34;");
        HTML_ISO_CHARS.put('¿', "&iquest;");
        HTML_ISO_CHARS.put('×', "&times;");
        HTML_ISO_CHARS.put('÷', "&divide;");
        HTML_ISO_CHARS.put('À', "&Agrave;");
        HTML_ISO_CHARS.put('Á', "&Aacute;");
        HTML_ISO_CHARS.put('Â', "&Acirc;");
        HTML_ISO_CHARS.put('Ã', "&Atilde;");
        HTML_ISO_CHARS.put('Ä', "&Auml;");
        HTML_ISO_CHARS.put('Å', "&Aring;");
        HTML_ISO_CHARS.put('Æ', "&AElig;");
        HTML_ISO_CHARS.put('Ç', "&Ccedil;");
        HTML_ISO_CHARS.put('È', "&Egrave;");
        HTML_ISO_CHARS.put('É', "&Eacute;");
        HTML_ISO_CHARS.put('Ê', "&Ecirc;");
        HTML_ISO_CHARS.put('Ë', "&Euml;");
        HTML_ISO_CHARS.put('Ì', "&Igrave;");
        HTML_ISO_CHARS.put('Í', "&Iacute;");
        HTML_ISO_CHARS.put('Î', "&Icirc;");
        HTML_ISO_CHARS.put('Ï', "&Iuml;");
        HTML_ISO_CHARS.put('Ð', "&ETH;");
        HTML_ISO_CHARS.put('Ñ', "&Ntilde;");
        HTML_ISO_CHARS.put('Ò', "&Ograve;");
        HTML_ISO_CHARS.put('Ó', "&Oacute;");
        HTML_ISO_CHARS.put('Ô', "&Ocirc;");
        HTML_ISO_CHARS.put('Õ', "&Otilde;");
        HTML_ISO_CHARS.put('Ö', "&Ouml;");
        HTML_ISO_CHARS.put('Ø', "&Oslash;");
        HTML_ISO_CHARS.put('Ù', "&Ugrave;");
        HTML_ISO_CHARS.put('Ú', "&Uacute;");
        HTML_ISO_CHARS.put('Û', "&Ucirc;");
        HTML_ISO_CHARS.put('Ü', "&Uuml;");
        HTML_ISO_CHARS.put('Ý', "&Yacute;");
        HTML_ISO_CHARS.put('Þ', "&THORN;");
        HTML_ISO_CHARS.put('ß', "&szlig;");
        HTML_ISO_CHARS.put('à', "&agrave;");
        HTML_ISO_CHARS.put('á', "&aacute;");
        HTML_ISO_CHARS.put('â', "&acirc;");
        HTML_ISO_CHARS.put('ã', "&atilde;");
        HTML_ISO_CHARS.put('ä', "&auml;");
        HTML_ISO_CHARS.put('å', "&aring;");
        HTML_ISO_CHARS.put('æ', "&aelig;");
        HTML_ISO_CHARS.put('ç', "&ccedil;");
        HTML_ISO_CHARS.put('è', "&egrave;");
        HTML_ISO_CHARS.put('é', "&eacute;");
        HTML_ISO_CHARS.put('ê', "&ecirc;");
        HTML_ISO_CHARS.put('ë', "&euml;");
        HTML_ISO_CHARS.put('ì', "&igrave;");
        HTML_ISO_CHARS.put('í', "&iacute;");
        HTML_ISO_CHARS.put('î', "&icirc;");
        HTML_ISO_CHARS.put('ï', "&iuml;");
        HTML_ISO_CHARS.put('ð', "&eth;");
        HTML_ISO_CHARS.put('ñ', "&ntilde;");
        HTML_ISO_CHARS.put('ò', "&ograve;");
        HTML_ISO_CHARS.put('ó', "&oacute;");
        HTML_ISO_CHARS.put('ô', "&ocirc;");
        HTML_ISO_CHARS.put('õ', "&otilde;");
        HTML_ISO_CHARS.put('ö', "&ouml;");
        HTML_ISO_CHARS.put('ø', "&oslash;");
        HTML_ISO_CHARS.put('ù', "&ugrave;");
        HTML_ISO_CHARS.put('ú', "&uacute;");
        HTML_ISO_CHARS.put('û', "&ucirc;");
        HTML_ISO_CHARS.put('ü', "&uuml;");
        HTML_ISO_CHARS.put('ý', "&yacute;");
        HTML_ISO_CHARS.put('þ', "&thorn;");
        HTML_ISO_CHARS.put('ÿ', "&yuml;");

        HTML_ISO_ENTITIES.put("&quot;", '"');
        HTML_ISO_ENTITIES.put("&apos;", '\'');
//        HTML_ISO_ENTITIES.put("&amp;", '&');
        HTML_ISO_ENTITIES.put("&lt;", '<');
        HTML_ISO_ENTITIES.put("&gt;", '>');
        HTML_ISO_ENTITIES.put("&nbsp;", ' ');
        HTML_ISO_ENTITIES.put("&iexcl;", '¡');
        HTML_ISO_ENTITIES.put("&cent;", '¢');
        HTML_ISO_ENTITIES.put("&pound;", '£');
        HTML_ISO_ENTITIES.put("&curren;", '¤');
        HTML_ISO_ENTITIES.put("&yen;", '¥');
        HTML_ISO_ENTITIES.put("&brvbar;", '¦');
        HTML_ISO_ENTITIES.put("&sect;", '§');
        HTML_ISO_ENTITIES.put("&uml;", '¨');
        HTML_ISO_ENTITIES.put("&copy;", '©');
        HTML_ISO_ENTITIES.put("&ordf;", 'ª');
        HTML_ISO_ENTITIES.put("&laquo;", '«');
        HTML_ISO_ENTITIES.put("&not;", '¬');
        HTML_ISO_ENTITIES.put("&shy;", '�');
        HTML_ISO_ENTITIES.put("&reg;", '®');
        HTML_ISO_ENTITIES.put("&macr;", '¯');
        HTML_ISO_ENTITIES.put("&deg;", '°');
        HTML_ISO_ENTITIES.put("&plusmn;", '±');
        HTML_ISO_ENTITIES.put("&sup2;", '²');
        HTML_ISO_ENTITIES.put("&sup3;", '³');
        HTML_ISO_ENTITIES.put("&acute;", '´');
        HTML_ISO_ENTITIES.put("&micro;", 'µ');
        HTML_ISO_ENTITIES.put("&para;", '¶');
        HTML_ISO_ENTITIES.put("&middot;", '·');
        HTML_ISO_ENTITIES.put("&cedil;", '¸');
        HTML_ISO_ENTITIES.put("&sup1;", '¹');
        HTML_ISO_ENTITIES.put("&ordm;", 'º');
        HTML_ISO_ENTITIES.put("&raquo;", '»');
        HTML_ISO_ENTITIES.put("&frac14;", '¼');
        HTML_ISO_ENTITIES.put("&frac12;", '½');
        HTML_ISO_ENTITIES.put("&frac34;", '¾');
        HTML_ISO_ENTITIES.put("&iquest;", '¿');
        HTML_ISO_ENTITIES.put("&times;", '×');
        HTML_ISO_ENTITIES.put("&divide;", '÷');
        HTML_ISO_ENTITIES.put("&Agrave;", 'À');
        HTML_ISO_ENTITIES.put("&Aacute;", 'Á');
        HTML_ISO_ENTITIES.put("&Acirc;", 'Â');
        HTML_ISO_ENTITIES.put("&Atilde;", 'Ã');
        HTML_ISO_ENTITIES.put("&Auml;", 'Ä');
        HTML_ISO_ENTITIES.put("&Aring;", 'Å');
        HTML_ISO_ENTITIES.put("&AElig;", 'Æ');
        HTML_ISO_ENTITIES.put("&Ccedil;", 'Ç');
        HTML_ISO_ENTITIES.put("&Egrave;", 'È');
        HTML_ISO_ENTITIES.put("&Eacute;", 'É');
        HTML_ISO_ENTITIES.put("&Ecirc;", 'Ê');
        HTML_ISO_ENTITIES.put("&Euml;", 'Ë');
        HTML_ISO_ENTITIES.put("&Igrave;", 'Ì');
        HTML_ISO_ENTITIES.put("&Iacute;", 'Í');
        HTML_ISO_ENTITIES.put("&Icirc;", 'Î');
        HTML_ISO_ENTITIES.put("&Iuml;", 'Ï');
        HTML_ISO_ENTITIES.put("&ETH;", 'Ð');
        HTML_ISO_ENTITIES.put("&Ntilde;", 'Ñ');
        HTML_ISO_ENTITIES.put("&Ograve;", 'Ò');
        HTML_ISO_ENTITIES.put("&Oacute;", 'Ó');
        HTML_ISO_ENTITIES.put("&Ocirc;", 'Ô');
        HTML_ISO_ENTITIES.put("&Otilde;", 'Õ');
        HTML_ISO_ENTITIES.put("&Ouml;", 'Ö');
        HTML_ISO_ENTITIES.put("&Oslash;", 'Ø');
        HTML_ISO_ENTITIES.put("&Ugrave;", 'Ù');
        HTML_ISO_ENTITIES.put("&Uacute;", 'Ú');
        HTML_ISO_ENTITIES.put("&Ucirc;", 'Û');
        HTML_ISO_ENTITIES.put("&Uuml;", 'Ü');
        HTML_ISO_ENTITIES.put("&Yacute;", 'Ý');
        HTML_ISO_ENTITIES.put("&THORN;", 'Þ');
        HTML_ISO_ENTITIES.put("&szlig;", 'ß');
        HTML_ISO_ENTITIES.put("&agrave;", 'à');
        HTML_ISO_ENTITIES.put("&aacute;", 'á');
        HTML_ISO_ENTITIES.put("&acirc;", 'â');
        HTML_ISO_ENTITIES.put("&atilde;", 'ã');
        HTML_ISO_ENTITIES.put("&auml;", 'ä');
        HTML_ISO_ENTITIES.put("&aring;", 'å');
        HTML_ISO_ENTITIES.put("&aelig;", 'æ');
        HTML_ISO_ENTITIES.put("&ccedil;", 'ç');
        HTML_ISO_ENTITIES.put("&egrave;", 'è');
        HTML_ISO_ENTITIES.put("&eacute;", 'é');
        HTML_ISO_ENTITIES.put("&ecirc;", 'ê');
        HTML_ISO_ENTITIES.put("&euml;", 'ë');
        HTML_ISO_ENTITIES.put("&igrave;", 'ì');
        HTML_ISO_ENTITIES.put("&iacute;", 'í');
        HTML_ISO_ENTITIES.put("&icirc;", 'î');
        HTML_ISO_ENTITIES.put("&iuml;", 'ï');
        HTML_ISO_ENTITIES.put("&eth;", 'ð');
        HTML_ISO_ENTITIES.put("&ntilde;", 'ñ');
        HTML_ISO_ENTITIES.put("&ograve;", 'ò');
        HTML_ISO_ENTITIES.put("&oacute;", 'ó');
        HTML_ISO_ENTITIES.put("&ocirc;", 'ô');
        HTML_ISO_ENTITIES.put("&otilde;", 'õ');
        HTML_ISO_ENTITIES.put("&ouml;", 'ö');
        HTML_ISO_ENTITIES.put("&oslash;", 'ø');
        HTML_ISO_ENTITIES.put("&ugrave;", 'ù');
        HTML_ISO_ENTITIES.put("&uacute;", 'ú');
        HTML_ISO_ENTITIES.put("&ucirc;", 'û');
        HTML_ISO_ENTITIES.put("&uuml;", 'ü');
        HTML_ISO_ENTITIES.put("&yacute;", 'ý');
        HTML_ISO_ENTITIES.put("&thorn;", 'þ');
        HTML_ISO_ENTITIES.put("&yuml;", 'ÿ');
    }

    /**
     * @see http://www.w3schools.com/tags/ref_entities.asp
     * @param string
     * @return Class StringEntity org.apache.http.entity.AbstractHttpEntity
     * org.apache.http.entity.StringEntity
     */
    public static String htmlIso8859encode(String string) {
        if (isNull(string)) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        for (char achar : string.toCharArray()) {
            if (HTML_ISO_CHARS.containsKey(achar)) {
                sb.append(HTML_ISO_CHARS.get(achar));
            } else {
                sb.append(achar);
            }
        }
        return sb.toString();
    }

    public static String htmlIso8859decode(String string) {
        if (isNull(string)) {
            return string;
        }
//        StringBuilder sb = new StringBuilder();
        for (String entity : HTML_ISO_ENTITIES.keySet()) {
            if (string.contains(entity)) {
                string = string.replaceAll(entity, HTML_ISO_ENTITIES.get(entity).toString());
            }
        }
        return string;
    }

    public static boolean isNull(String str) {
        return (str == null || str.trim().equals(""));
    }
}
