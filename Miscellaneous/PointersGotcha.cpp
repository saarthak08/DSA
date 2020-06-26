#include <iostream>

using namespace std;

void gotcha(int *x);
int *gotchaFix1(int *x);
void gotchaFix2(int **x);
void gotchaFix3(int *&x);
void anotherGotcha(int *x);

int main()
{
    int *p = NULL;

    gotcha(p);

    if (p == NULL)
    {
        cout << "\nGotcha! The value of pointer p is NULL.\nWe have allocated value to pointer x in gotcha function via new which allocates memory to heap but still pointer p is NULL in main function. " << endl;
        cout << "This is because pointer x in gotcha function is only a copy of pointer p in main function. Pointer x in gotcha function points to the assigned value but pointer p in main function still points to NULL." << endl;
        cout << "This can be fixed by 3 ways: \n1) Returning pointer x from gotcha function and assigning it to pointer p in main function" << endl;
        cout << "2) Passing double pointer i.e. a pointer to pointer p to the gotcha function and then assigning value to pointer p from gotcha function." << endl;
        cout << "3) Passing value by reference of pointer p from main function to gotcha functions." << endl;
        cout<<"\nOne of the main gotcha of pointers is that the pointer inside a function should be assigned value with new or malloc only. As new or malloc assigns memory in heap.\nIf we don't use new or malloc and rather directly assign value to pointers (e.g.: int a=2; int *p=&a;), none of the above 3 methods will work as this assigns memory in stack which gets destroyed after returning of functions." << endl;
    }
    cout << "\n\nFix 1:" << endl;
    p = gotchaFix1(p);
    cout << "Value of pointer p in main: " << *p << endl;

    cout << "\n\nFix 2:" << endl;
    gotchaFix2(&p);
    cout << "Value of pointer p in main: " << *p << endl;

    cout << "\n\nFix 3:" << endl;
    gotchaFix3(p);
    cout << "Value of pointer p in main: " << *p << endl;

    int a = 5;
    p = &a;
    // OR p = new int(5); BOTH WILL WORK.
    cout << "\n\nNow p is assigned value in main function. Value of pointer p in main before passing to anotherGotcha: " << *p << endl;
    anotherGotcha(p);
    cout << "Value of pointer p in main after passing to anotherGotcha: " << *p << endl;
    cout << "Now the value of pointer p changed after passing pointer to anotherGotcha and not using any of the previous fixes.\nThis is because pointer p is already initialised to a memory location in main. When it is passed to anotherGotcha, a copy of pointer p is made which also refers to the same memory location as pointed by the x. In the anotherGotcha function, we only changed the memory location's value. That's why it remain changed after returning to main function." << endl;
}

void gotcha(int *x)
{
    x = new int;
    cout << "Enter value you want to assign to pointer x in gotcha function: " << endl;
    cin >> *x;
    cout << "Pointer assigned!\nValue of pointer x in gotcha function: " << *x << endl;
}

int *gotchaFix1(int *x)
{
    x = new int;
    cout << "Enter value you want to assign to pointer x in gotchaFix1 function: " << endl;
    cin >> *x;
    cout << "Pointer assigned!\nValue of pointer x in gotchaFix1 function: " << *x << endl;
    return x;
}

void gotchaFix2(int **x)
{
    *x = new int;
    cout << "Enter value you want to assign to pointer x in gotchaFix2 function: " << endl;
    cin >> **x;
    cout << "Pointer assigned!\nValue of pointer x in gotchaFix2 function: " << **x << endl;
}

void gotchaFix3(int *&x)
{
    x = new int;
    cout << "Enter value you want to assign to pointer x in gotchaFix3 function: " << endl;
    cin >> *x;
    cout << "Pointer assigned!\nValue of pointer x in gotchaFix3 function: " << *x << endl;
}

void anotherGotcha(int *x)
{
    cout << "Enter value you want to assign to pointer x in anotherGotcha function: " << endl;
    cin >> *x;
    cout << "Pointer assigned!\nValue of pointer x in anotherGotcha function: " << *x << endl;
}
