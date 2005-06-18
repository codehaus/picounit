package picounit;

public interface DelegateVerify {
	<Type> DelegateVerifier<Type> delegateTo(Type ignore);

	interface DelegateVerifier<Type> {
		void whenCalling(Type actualValue);
	}
}