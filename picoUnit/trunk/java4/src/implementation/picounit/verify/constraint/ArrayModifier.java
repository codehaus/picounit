package picounit.verify.constraint;

public interface ArrayModifier extends Modifier {
	public Object modifyElement(Object element);

	ArrayModifier NULL = new ArrayModifier() {
		public Object modify(Object value) {
			return value;
		}

		public Object modifyElement(Object element) {
			return element;
		}

		public String getName() {
			return "";
		}
	};
}
