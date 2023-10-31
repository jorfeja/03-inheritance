package ohm.softa.a03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ohm.softa.a03.State.*;

public class Cat {
	private static final Logger logger = LogManager.getLogger();

	// valid states
	// public enum State {SLEEPING, HUNGRY, DIGESTING, PLAYFUL, DEAD}

	// initially, animals are sleeping
	// private State state = State.SLEEPING;

	private State state;

	// state durations (set via constructor), ie. the number of ticks in each state
	private final int sleep;
	private final int awake;
	private final int digest;

	private final String name;

	private int time = 0;
	private int timeDigesting = 0;

	public Cat(String name, int sleep, int awake, int digest) {
		this.name = name;
		this.sleep = sleep;
		this.awake = awake;
		this.digest = digest;

		state = new Sleeping(sleep);
	}

	/// Gets duration the cat has been awake

	public int getAwake() {
		return awake;
	}

	final public void tick() {
		state = state.tick(this);
	}

	/*
	public void tick2(){
		logger.info("tick()");
		time = time + 1;

		switch (state) {
			case SLEEPING:
				if (time == sleep) {
					logger.info("Yoan... getting hungry!");
					state = HUNGRY;
					time = 0;
				}
				break;
			case HUNGRY:
				if(time == awake){
					logger.info("I've starved for a too long time...good bye...");
					state = DEAD;
				}
				break;
			case DIGESTING:
				timeDigesting = timeDigesting + 1;
				if (timeDigesting == digest) {
					logger.info("Getting in a playful mood!");
					state = PLAYFUL;
				}
				break;
			case PLAYFUL:
				if (time >= awake) {
					logger.info("Yoan... getting tired!");
					state = SLEEPING;
					time = 0;
				}
				break;

			case DEAD:
				break;
			default:
				throw new IllegalStateException("Unknown cat state " + state.name());
		}

		logger.info(state.name());

	}
	*/

	/**
	 * This would be a user interaction: feed the cat to change its state!
	 * Returns the new state.
	 */
	public State feed() {
		// Switches to `Digesting` immediately after being fed, regardless of how long the cat has been hungry
		return new Digesting(state.getTime(), state.getDuration());
	}

	public boolean isAsleep() {
		return state instanceof Sleeping;
	}

	public boolean isPlayful() {
		return state instanceof Playful;
	}

	public boolean isHungry() {
		return state instanceof Hungry;
	}

	public boolean isDigesting() {
		return state instanceof Digesting;
	}

	public boolean isDead() {
		return state instanceof Dead;
	}

	@Override
	public String toString() {
		return name;
	}

}

