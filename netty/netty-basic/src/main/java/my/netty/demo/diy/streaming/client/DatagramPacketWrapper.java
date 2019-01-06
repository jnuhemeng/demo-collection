package my.netty.demo.diy.streaming.client;

import io.netty.channel.socket.DatagramPacket;

public class DatagramPacketWrapper implements Comparable<DatagramPacketWrapper> {

    private long sequenceNumber;

    private DatagramPacket packet;

    public DatagramPacketWrapper(){}

    public DatagramPacketWrapper(long sequenceNumber, DatagramPacket packet) {
        this.sequenceNumber = sequenceNumber;
        this.packet = packet;
    }

    public int compareTo(DatagramPacketWrapper object) {
        if(sequenceNumber < object.getSequenceNumber()) {
            return 1;
        } else if(sequenceNumber > object.getSequenceNumber()) {
            return -1;
        } else {
            return 0;
        }
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    public void setPacket(DatagramPacket packet) {
        this.packet = packet;
    }

    @Override
    public String toString() {
        return "DatagramPacketWrapper{" +
                "sequenceNumber=" + sequenceNumber +
                ", packet=" + packet +
                '}';
    }
}
