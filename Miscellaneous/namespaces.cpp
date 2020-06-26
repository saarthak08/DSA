#include<iostream>

using namespace std;

// Namespaces are used to group the global code into different scopes and regions.
// Namespaces are better than global code as they can prevent ambiguity especially when using multiple libraries.

namespace ns1 {

	int value = 23;

	int get_value() {
		return value;
	}

}

namespace ns2 {
	class abc {
	private:
		int hello;
	public:
		void set_hello(int x);
		int get_hello();
		abc() {}
		abc(int hello) {
			this->hello=hello;
		}
		~abc() {
			cout << "Destructor called." <<endl;
		}
	};

	void abc::set_hello(int x) {
		this->hello=x;
	}

	int abc::get_hello() {
		return this->hello;
	}
}
int main() {
	ns2::abc a(50);
	cout << a.get_hello() << endl;
	cout << ns1::get_value() << endl;
	int value=2;
	cout << value << endl;
	using namespace ns1;
	cout << value << endl;
	cout << get_value() << endl;

}