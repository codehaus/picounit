package picounit;

public interface DelegateVerify {
	BooleanDelegateVerifier delegateTo(boolean ignore);
	ByteDelegateVerifier delegateTo(byte ignore);
	CharacterDelegateVerifier delegateTo(char ignore);
	DoubleDelegateVerifier delegateTo(double ignore);
	FloatDelegateVerifier delegateTo(float ignore);
	IntegerDelegateVerifier delegateTo(int ignore);
	LongDelegateVerifier delegateTo(long ignore);
	ShortDelegateVerifier delegateTo(short ignore);

	StringDelegateVerifier delegateTo(String ignore);

	DelegateVerifier delegateTo(Object ignore);

	interface BooleanDelegateVerifier {
		void whenCalling(boolean actualValue);
	}
	
	interface ByteDelegateVerifier {
		void whenCalling(byte actualValue);
	}

	interface CharacterDelegateVerifier {
		void whenCalling(char actualValue);
	}

	interface DoubleDelegateVerifier {
		void whenCalling(double actualValue);
	}

	interface FloatDelegateVerifier {
		void whenCalling(float actualValue);
	}

	interface IntegerDelegateVerifier {
		void whenCalling(int actualValue);
	}

	interface LongDelegateVerifier {
		void whenCalling(long actualValue);
	}

	interface ShortDelegateVerifier {
		void whenCalling(short actualValue);
	}

	interface StringDelegateVerifier {
		void whenCalling(String actualValue);
	}

	interface DelegateVerifier {
		void whenCalling(Object actualValue);
	}	
}