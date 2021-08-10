package ui.sound;

import javax.sound.midi.MidiChannel;

// A MIDI channel
// Modelled after ChannelData class from SimpleDrawingPlayer
//          (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public class Channel {
    private MidiChannel channel;

    private int number;
    private int bend;
    private int reverb;
    private int pressure;
    private int velocity;

    // EFFECTS: constructs the MIDI channel
    public Channel(MidiChannel channel, int number) {
        this.channel = channel;
        this.number = number;
        bend = 64;
        reverb = 64;
        pressure = 64;
        velocity = 64;
    }

    // getter
    public MidiChannel getChannel() {
        return channel;
    }
}
