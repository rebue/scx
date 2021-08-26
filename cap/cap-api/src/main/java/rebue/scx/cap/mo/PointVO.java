package rebue.scx.cap.mo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class PointVO {
    private String secretKey;

    public int x;

    public int y;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public PointVO(final int x, final int y, final String secretKey) {
        this.secretKey = secretKey;
        this.x = x;
        this.y = y;
    }

    public PointVO() {
    }

    public PointVO(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public String toJsonString() {
        return String.format("{\"secretKey\":\"%s\",\"x\":%d,\"y\":%d}", secretKey, x, y);
    }

    public PointVO parse(final String jsonStr) {
        final Map<String, Object> m = new HashMap<String, Object>();
        Arrays.stream(jsonStr
                .replaceFirst(",\\{", "\\{")
                .replaceFirst("\\{", "")
                .replaceFirst("\\}", "")
                .replaceAll("\"", "")
                .split(",")).forEach(item -> {
            m.put(item.split(":")[0], item.split(":")[1]);
        });
        //PointVO d = new PointVO();
        setX(Double.valueOf("" + m.get("x")).intValue());
        setY(Double.valueOf("" + m.get("y")).intValue());
        setSecretKey(m.getOrDefault("secretKey", "") + "");
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PointVO pointVO = (PointVO) o;
        return x == pointVO.x && y == pointVO.y && Objects.equals(secretKey, pointVO.secretKey);
    }

    @Override
    public int hashCode() {

        return Objects.hash(secretKey, x, y);
    }
}
