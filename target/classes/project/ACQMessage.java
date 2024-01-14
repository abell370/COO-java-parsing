package hepl.masi.principsecu.model;

import java.util.Objects;

public class ACQMessage {

    private static String ACK = "ACK";
    private static String NAK = "NAK";

    public static boolean ok(String serverResponse) {
        String[] components = serverResponse.split(":");
        if (Objects.equals(components[0], "8")) {
            return components[components.length - 1].equals(ACK);
        } else {
            return false;
        }
    }

}
