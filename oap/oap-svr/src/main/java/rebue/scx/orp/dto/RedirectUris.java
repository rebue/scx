package rebue.scx.orp.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import rebue.scx.orp.mo.OapRedirectUriMo;

public class RedirectUris {

    private static final RedirectUris EMPTY = new RedirectUris(Collections.emptyList());

    private final List<String> data;

    public static RedirectUris empty()
    {
        return EMPTY;
    }

    public RedirectUris(List<OapRedirectUriMo> list)
    {
        data = list.stream().map(OapRedirectUriMo::getRedirectUri).collect(Collectors.toList());
    }

    public boolean match(String target)
    {
        for (String db : data) {
            if (db.equalsIgnoreCase(target)) {
                return true;
            } else if (db.endsWith("*")) {
                String prefix = db.substring(0, db.length() - 1).toLowerCase();
                if (target.toLowerCase().startsWith(prefix)) {
                    return true;
                }
            }
        }
        return false;
    }

}
