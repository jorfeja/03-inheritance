package ohm.softa.a03;

import static ohm.softa.a03.Cat.*;

public abstract class State {
    private int time = 0;
    final private int duration;

    public State(int duration) {
        this.duration = duration;
    }

    public State(int time, int duration) {
        this.time = time;
        this.duration = duration;
    }

    public State tick(Cat cat) {
        time = time + 1;

        // If state is over start following state
        if (time == duration) {
            return sucessor(cat);
        }

        // TODO: Always returns state, return previous state if state doesn't change
        return null;
    }

    /// The next state, abstract bc it's defined differently for every state:
    public abstract State sucessor(Cat cat);

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }
}

class Digesting extends State {
    // TODO: Logic
    public Digesting(int time, int duration) {
        super(time, duration);
    }

    @Override
    public State sucessor(Cat cat) {
        return new Playful(getDuration());
    }
}

class Hungry extends State {
    // TODO: Logic
    public Hungry(int duration) {
        super(duration);
    }

    // If fed, return Digesting, else return Dead
    @Override
    public State sucessor(Cat cat) {
        if (getTime() == cat.getAwake()) {
            return new Dead();
        } else {
            return new Digesting(getTime(), getDuration());
        }
    }
}

class Playful extends State {
    // TODO: Logic
    public Playful(int duration) {
        super(duration);
    }

    @Override
    public State sucessor(Cat cat) {
        return new Sleeping(getDuration());
    }
}

class Sleeping extends State {
    // TODO: Logic
    public Sleeping(int duration) {
        super(duration);
    }

    @Override
    public State sucessor(Cat cat) {
        // Is hungry for as long as cat is awake
        return new Hungry(cat.getAwake());
    }
}

class Dead extends State {
    // TODO: Logic
    public Dead() {
        // Death is infinite
        super(Integer.MAX_VALUE);
    }

    // Who knows?
    @Override
    public State sucessor(Cat cat) {
        return null;
    }
}
