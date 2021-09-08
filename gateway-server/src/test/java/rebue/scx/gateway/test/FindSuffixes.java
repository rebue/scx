package rebue.scx.gateway.test;

import org.junit.Test;

import java.io.File;
import java.util.*;

public class FindSuffixes {

    private static final String BASE = "C:\\Users\\lq\\Desktop\\nginx-html\\html\\admin-web";

    @Test
    public void main() throws Exception
    {
        Set<String> suffixes = new HashSet<>();
        Queue<File> q = new LinkedList<>();
        q.add(new File(BASE));
        while (!q.isEmpty()) {
            File poll = q.poll();
            if (poll.isDirectory()) {
                q.addAll(Arrays.asList(poll.listFiles()));
            } else {
                int idx = poll.getName().lastIndexOf(".");
                suffixes.add(poll.getName().substring(idx + 1));
            }
        }
        StringBuilder sb = new StringBuilder(".*[.](");
        for (String suffix : suffixes) {
            sb.append(suffix).append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")$");
        System.out.println(sb);
    }

}
