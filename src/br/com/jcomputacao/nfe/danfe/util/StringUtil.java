package br.com.jcomputacao.nfe.danfe.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author murilo.lima
 */
public class StringUtil {

    private static final Map<Character, String> htmlIsoChars = new HashMap<Character, String>();
    private static final Map<String, Character> htmlIsoEntities = new HashMap<String, Character>();

    static {
        htmlIsoChars.put('"', "&quot;");
        htmlIsoChars.put('\'', "&apos;");
//        htmlIsoChars.put('&', "&amp;");
        htmlIsoChars.put('<', "&lt;");
        htmlIsoChars.put('>', "&gt;");
//        htmlIsoChars.put(' ',"&nbsp;");
        htmlIsoChars.put('¡', "&iexcl;");
        htmlIsoChars.put('¢', "&cent;");
        htmlIsoChars.put('£', "&pound;");
        htmlIsoChars.put('¤', "&curren;");
        htmlIsoChars.put('¥', "&yen;");
        htmlIsoChars.put('¦', "&brvbar;");
        htmlIsoChars.put('§', "&sect;");
        htmlIsoChars.put('¨', "&uml;");
        htmlIsoChars.put('©', "&copy;");
        htmlIsoChars.put('ª', "&ordf;");
        htmlIsoChars.put('«', "&laquo;");
        htmlIsoChars.put('¬', "&not;");
        htmlIsoChars.put('�', "&shy;");
        htmlIsoChars.put('®', "&reg;");
        htmlIsoChars.put('¯', "&macr;");
        htmlIsoChars.put('°', "&deg;");
        htmlIsoChars.put('±', "&plusmn;");
        htmlIsoChars.put('²', "&sup2;");
        htmlIsoChars.put('³', "&sup3;");
        htmlIsoChars.put('´', "&acute;");
        htmlIsoChars.put('µ', "&micro;");
        htmlIsoChars.put('¶', "&para;");
        htmlIsoChars.put('·', "&middot;");
        htmlIsoChars.put('¸', "&cedil;");
        htmlIsoChars.put('¹', "&sup1;");
        htmlIsoChars.put('º', "&ordm;");
        htmlIsoChars.put('»', "&raquo;");
        htmlIsoChars.put('¼', "&frac14;");
        htmlIsoChars.put('½', "&frac12;");
        htmlIsoChars.put('¾', "&frac34;");
        htmlIsoChars.put('¿', "&iquest;");
        htmlIsoChars.put('×', "&times;");
        htmlIsoChars.put('÷', "&divide;");
        htmlIsoChars.put('À', "&Agrave;");
        htmlIsoChars.put('Á', "&Aacute;");
        htmlIsoChars.put('Â', "&Acirc;");
        htmlIsoChars.put('Ã', "&Atilde;");
        htmlIsoChars.put('Ä', "&Auml;");
        htmlIsoChars.put('Å', "&Aring;");
        htmlIsoChars.put('Æ', "&AElig;");
        htmlIsoChars.put('Ç', "&Ccedil;");
        htmlIsoChars.put('È', "&Egrave;");
        htmlIsoChars.put('É', "&Eacute;");
        htmlIsoChars.put('Ê', "&Ecirc;");
        htmlIsoChars.put('Ë', "&Euml;");
        htmlIsoChars.put('Ì', "&Igrave;");
        htmlIsoChars.put('Í', "&Iacute;");
        htmlIsoChars.put('Î', "&Icirc;");
        htmlIsoChars.put('Ï', "&Iuml;");
        htmlIsoChars.put('Ð', "&ETH;");
        htmlIsoChars.put('Ñ', "&Ntilde;");
        htmlIsoChars.put('Ò', "&Ograve;");
        htmlIsoChars.put('Ó', "&Oacute;");
        htmlIsoChars.put('Ô', "&Ocirc;");
        htmlIsoChars.put('Õ', "&Otilde;");
        htmlIsoChars.put('Ö', "&Ouml;");
        htmlIsoChars.put('Ø', "&Oslash;");
        htmlIsoChars.put('Ù', "&Ugrave;");
        htmlIsoChars.put('Ú', "&Uacute;");
        htmlIsoChars.put('Û', "&Ucirc;");
        htmlIsoChars.put('Ü', "&Uuml;");
        htmlIsoChars.put('Ý', "&Yacute;");
        htmlIsoChars.put('Þ', "&THORN;");
        htmlIsoChars.put('ß', "&szlig;");
        htmlIsoChars.put('à', "&agrave;");
        htmlIsoChars.put('á', "&aacute;");
        htmlIsoChars.put('â', "&acirc;");
        htmlIsoChars.put('ã', "&atilde;");
        htmlIsoChars.put('ä', "&auml;");
        htmlIsoChars.put('å', "&aring;");
        htmlIsoChars.put('æ', "&aelig;");
        htmlIsoChars.put('ç', "&ccedil;");
        htmlIsoChars.put('è', "&egrave;");
        htmlIsoChars.put('é', "&eacute;");
        htmlIsoChars.put('ê', "&ecirc;");
        htmlIsoChars.put('ë', "&euml;");
        htmlIsoChars.put('ì', "&igrave;");
        htmlIsoChars.put('í', "&iacute;");
        htmlIsoChars.put('î', "&icirc;");
        htmlIsoChars.put('ï', "&iuml;");
        htmlIsoChars.put('ð', "&eth;");
        htmlIsoChars.put('ñ', "&ntilde;");
        htmlIsoChars.put('ò', "&ograve;");
        htmlIsoChars.put('ó', "&oacute;");
        htmlIsoChars.put('ô', "&ocirc;");
        htmlIsoChars.put('õ', "&otilde;");
        htmlIsoChars.put('ö', "&ouml;");
        htmlIsoChars.put('ø', "&oslash;");
        htmlIsoChars.put('ù', "&ugrave;");
        htmlIsoChars.put('ú', "&uacute;");
        htmlIsoChars.put('û', "&ucirc;");
        htmlIsoChars.put('ü', "&uuml;");
        htmlIsoChars.put('ý', "&yacute;");
        htmlIsoChars.put('þ', "&thorn;");
        htmlIsoChars.put('ÿ', "&yuml;");

        htmlIsoEntities.put("&quot;", '"');
        htmlIsoEntities.put("&apos;", '\'');
//        htmlIsoEntities.put("&amp;", '&');
        htmlIsoEntities.put("&lt;", '<');
        htmlIsoEntities.put("&gt;", '>');
        htmlIsoEntities.put("&nbsp;", ' ');
        htmlIsoEntities.put("&iexcl;", '¡');
        htmlIsoEntities.put("&cent;", '¢');
        htmlIsoEntities.put("&pound;", '£');
        htmlIsoEntities.put("&curren;", '¤');
        htmlIsoEntities.put("&yen;", '¥');
        htmlIsoEntities.put("&brvbar;", '¦');
        htmlIsoEntities.put("&sect;", '§');
        htmlIsoEntities.put("&uml;", '¨');
        htmlIsoEntities.put("&copy;", '©');
        htmlIsoEntities.put("&ordf;", 'ª');
        htmlIsoEntities.put("&laquo;", '«');
        htmlIsoEntities.put("&not;", '¬');
        htmlIsoEntities.put("&shy;", '�');
        htmlIsoEntities.put("&reg;", '®');
        htmlIsoEntities.put("&macr;", '¯');
        htmlIsoEntities.put("&deg;", '°');
        htmlIsoEntities.put("&plusmn;", '±');
        htmlIsoEntities.put("&sup2;", '²');
        htmlIsoEntities.put("&sup3;", '³');
        htmlIsoEntities.put("&acute;", '´');
        htmlIsoEntities.put("&micro;", 'µ');
        htmlIsoEntities.put("&para;", '¶');
        htmlIsoEntities.put("&middot;", '·');
        htmlIsoEntities.put("&cedil;", '¸');
        htmlIsoEntities.put("&sup1;", '¹');
        htmlIsoEntities.put("&ordm;", 'º');
        htmlIsoEntities.put("&raquo;", '»');
        htmlIsoEntities.put("&frac14;", '¼');
        htmlIsoEntities.put("&frac12;", '½');
        htmlIsoEntities.put("&frac34;", '¾');
        htmlIsoEntities.put("&iquest;", '¿');
        htmlIsoEntities.put("&times;", '×');
        htmlIsoEntities.put("&divide;", '÷');
        htmlIsoEntities.put("&Agrave;", 'À');
        htmlIsoEntities.put("&Aacute;", 'Á');
        htmlIsoEntities.put("&Acirc;", 'Â');
        htmlIsoEntities.put("&Atilde;", 'Ã');
        htmlIsoEntities.put("&Auml;", 'Ä');
        htmlIsoEntities.put("&Aring;", 'Å');
        htmlIsoEntities.put("&AElig;", 'Æ');
        htmlIsoEntities.put("&Ccedil;", 'Ç');
        htmlIsoEntities.put("&Egrave;", 'È');
        htmlIsoEntities.put("&Eacute;", 'É');
        htmlIsoEntities.put("&Ecirc;", 'Ê');
        htmlIsoEntities.put("&Euml;", 'Ë');
        htmlIsoEntities.put("&Igrave;", 'Ì');
        htmlIsoEntities.put("&Iacute;", 'Í');
        htmlIsoEntities.put("&Icirc;", 'Î');
        htmlIsoEntities.put("&Iuml;", 'Ï');
        htmlIsoEntities.put("&ETH;", 'Ð');
        htmlIsoEntities.put("&Ntilde;", 'Ñ');
        htmlIsoEntities.put("&Ograve;", 'Ò');
        htmlIsoEntities.put("&Oacute;", 'Ó');
        htmlIsoEntities.put("&Ocirc;", 'Ô');
        htmlIsoEntities.put("&Otilde;", 'Õ');
        htmlIsoEntities.put("&Ouml;", 'Ö');
        htmlIsoEntities.put("&Oslash;", 'Ø');
        htmlIsoEntities.put("&Ugrave;", 'Ù');
        htmlIsoEntities.put("&Uacute;", 'Ú');
        htmlIsoEntities.put("&Ucirc;", 'Û');
        htmlIsoEntities.put("&Uuml;", 'Ü');
        htmlIsoEntities.put("&Yacute;", 'Ý');
        htmlIsoEntities.put("&THORN;", 'Þ');
        htmlIsoEntities.put("&szlig;", 'ß');
        htmlIsoEntities.put("&agrave;", 'à');
        htmlIsoEntities.put("&aacute;", 'á');
        htmlIsoEntities.put("&acirc;", 'â');
        htmlIsoEntities.put("&atilde;", 'ã');
        htmlIsoEntities.put("&auml;", 'ä');
        htmlIsoEntities.put("&aring;", 'å');
        htmlIsoEntities.put("&aelig;", 'æ');
        htmlIsoEntities.put("&ccedil;", 'ç');
        htmlIsoEntities.put("&egrave;", 'è');
        htmlIsoEntities.put("&eacute;", 'é');
        htmlIsoEntities.put("&ecirc;", 'ê');
        htmlIsoEntities.put("&euml;", 'ë');
        htmlIsoEntities.put("&igrave;", 'ì');
        htmlIsoEntities.put("&iacute;", 'í');
        htmlIsoEntities.put("&icirc;", 'î');
        htmlIsoEntities.put("&iuml;", 'ï');
        htmlIsoEntities.put("&eth;", 'ð');
        htmlIsoEntities.put("&ntilde;", 'ñ');
        htmlIsoEntities.put("&ograve;", 'ò');
        htmlIsoEntities.put("&oacute;", 'ó');
        htmlIsoEntities.put("&ocirc;", 'ô');
        htmlIsoEntities.put("&otilde;", 'õ');
        htmlIsoEntities.put("&ouml;", 'ö');
        htmlIsoEntities.put("&oslash;", 'ø');
        htmlIsoEntities.put("&ugrave;", 'ù');
        htmlIsoEntities.put("&uacute;", 'ú');
        htmlIsoEntities.put("&ucirc;", 'û');
        htmlIsoEntities.put("&uuml;", 'ü');
        htmlIsoEntities.put("&yacute;", 'ý');
        htmlIsoEntities.put("&thorn;", 'þ');
        htmlIsoEntities.put("&yuml;", 'ÿ');
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
            if (htmlIsoChars.containsKey(achar)) {
                sb.append(htmlIsoChars.get(achar));
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
        for (String entity : htmlIsoEntities.keySet()) {
            if (string.contains(entity)) {
                string = string.replaceAll(entity, htmlIsoEntities.get(entity).toString());
            }
        }
        return string;
    }

    public static boolean isNull(String str) {
        return (str == null || str.trim().equals(""));
    }
}
