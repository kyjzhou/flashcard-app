package ui.sound;

import javax.sound.midi.*;
import java.util.HashMap;
import java.util.Map;

// A MIDISynth system
// Modelled after MidiSynth class from SimpleDrawingPlayer
//          (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public class MidiSynth {
    private Synthesizer synthesizer;
    private Instrument[] instruments;
    private Channel[] channels;

    // MODIFIES: this
    // EFFECTS: sets up synthesizer, instruments, and channels
    public void open() {
        getSynthesizer();
        setupInstruments();
        setupChannels();
    }

    // EFFECTS: plays sound from given instrument with given note and velocity
    public void play(int instrument, int note, int velocity) {
        Channel channel = getChannel(instrument);
        MidiChannel midiChannel = channel.getChannel();
        midiChannel.noteOn(note, velocity);
    }

    // EFFECTS: stops playing sound from instrument
    public void stop(int instrument, int note) {
        Channel channel = getChannel(instrument);
        MidiChannel midiChannel = channel.getChannel();
        midiChannel.noteOff(note, 0);
    }

    // MODIFIES: this
    // EFFECTS: sets up channels for this MidiSynth
    private void setupChannels() {
        MidiChannel[] midiChannels = synthesizer.getChannels();
        channels = new Channel[midiChannels.length];
        for (int i = 0; i < channels.length; i++) {
            channels[i] = new Channel(midiChannels[i], i);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up instruments for this MidiSynth
    private void setupInstruments() {
        if (getSoundBank() != null) {
            instruments = synthesizer.getDefaultSoundbank().getInstruments();
            synthesizer.loadInstrument(instruments[0]);
        }
    }

    // EFFECTS: gets sound bank from synthesizer
    private Soundbank getSoundBank() {
        return synthesizer.getDefaultSoundbank();
    }

    // EFFECTS: opens and sets up synthesizer
    private void getSynthesizer() {
        try {
            if (synthesizer == null) {
                if ((synthesizer = MidiSystem.getSynthesizer()) == null) {
                    System.out.println("Error with getting synthesizer");
                    return;
                }
            }
            synthesizer.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: returns the channel of given instrument
    private Channel getChannel(int instrument) {
        Map<Integer, Channel> channels = new HashMap<>();
        Channel channel = channels.get(instrument);
        if (channel == null) {
            channel = getSpecifiedChannel(channels.size());
            MidiChannel midiChannel = channel.getChannel();
            midiChannel.programChange(instrument);
            channels.put(instrument, channel);
        }
        return channel;
    }

    // EFFECTS: returns the channel at given index
    public Channel getSpecifiedChannel(int index) {
        return channels[index];
    }

}
