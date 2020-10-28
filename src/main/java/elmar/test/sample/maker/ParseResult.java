package elmar.test.sample.maker;

public enum ParseResult {

	SUCCESS_CONTINUE(true, false), SUCCESS_STOP(true, true), FAIL_CONTINUE(false, false), FAIL_STOP(false, true);

	private boolean success;
	private boolean stop;

	private ParseResult(boolean success, boolean stop) {
		this.success = success;
		this.stop = stop;
	}

	public boolean isSuccess() {
		return success;
	}

	public boolean isStop() {
		return stop;
	}

}
