package my.netty.demo.diy.ondemand.common;

/**
 * 报文实体类报文实体类报文实体类报文实体类Message entity
 */
public class Message {

    private byte version;

    private byte type;

    private int length;

    private String payload;

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "version=" + version +
                ", type=" + type +
                ", length=" + length +
                ", payload='" + payload + '\'' +
                '}';
    }
}
