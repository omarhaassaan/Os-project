#include<iostream>
#include<cstdlib>
#include<cmath>
using namespace std;



int waiting(int arrivaltime[], int bursttime[], int t, int i)
{
	int waitingtime = t - bursttime[i] - arrivaltime[i];
	return waitingtime;
}

int calc(int ariv[], int com, int index)
{
	return  com - ariv[index];
}


void operation(int arrivaltime[], int bursttime[], int n, int q1, int q2) //round robin 1
{
	int t = 0; // array of time -----> to calculate each process execution time

	int copyBurst[20] = { 0 }; // copy original burst array into another array to use the copy one in calculating the waiting time
	for (int i = 0; i < n; i++)
	{
		copyBurst[i] = bursttime[i];
	}

	for (int i = 0; i < n; i++)
	{
		if (arrivaltime[i] <= t)
		{
			if (bursttime[i] <= 0)
			{
				continue;
			}
			else
			{
				if (bursttime[i] <= q1)
				{
					t += bursttime[i];
					int waitingTime = waiting(arrivaltime, copyBurst, t, i); // function to caculate the waiting time 

					bursttime[i] = 0;
					cout << "process " << i + 1 << " finish execution at " << t << " milliseconds."
						<< "Waiting Time: " << waitingTime << " milliseconds " << endl;
				}
				else
				{
					t += q1;
					bursttime[i] -= q1;
				}
			}
		}
		else
		{
			int diff = arrivaltime[i] - t;
			cout << endl;
			cout << "There is no processes in the CPU for " << diff << " milliseconds" << endl;
			cout << endl;
			t += diff; // add difference between (arrivaltime[i] and t) to the t  and start executing the coming process

			if (bursttime[i] <= 0)
			{
				continue;
			}
			else
			{
				if (bursttime[i] <= q1)
				{
					t += bursttime[i];
					int waitingTime = waiting(arrivaltime, copyBurst, t, i); // function to caculate the waiting time 

					bursttime[i] = 0;
					cout << "process " << i + 1 << " finish execution at " << t << " milliseconds."
						<< "Waiting Time: " << waitingTime << " milliseconds " << endl;
				}
				else
				{
					t += q1;
					bursttime[i] -= q1;
				}

			}
		}
	}


	// process which have greater arrival time At>time






	// round robin 2
	for (int i = 0; i < n; i++)
	{

		if (bursttime[i] <= 0)
		{
			continue;
		}
		else
		{
			if (bursttime[i] <= q2)
			{
				t += bursttime[i];
				int waitingTime = waiting(arrivaltime, copyBurst, t, i);
				bursttime[i] = 0;
				cout << "process " << i + 1 << " finish execution at " << t << " milliseconds."
					<< "Waiting Time: " << waitingTime << " milliseconds " << endl;
			}
			else
			{
				t += q2;
				bursttime[i] -= q2;
			}
		}

	}



	//FCFS

	for (int i = 0; i < n; i++)
	{


		if (bursttime[i] > 0)
		{
			t += bursttime[i];
			int waitingTime = waiting(arrivaltime, copyBurst, t, i);
			bursttime[i] = 0;
			cout << "process " << i + 1 << " finish execution at " << t << " milliseconds."
				<< "Waiting Time: " << waitingTime << " milliseconds " << endl;
		}


	}

}


int min_element(int arr[], int n)  // if arrivaltime[i] starts with a number more than 0
{
	int min = arr[0];

	for (int i = 0; i < n; i++) {

		if (arr[i] < min)
		{
			min = arr[i];
		}

	}
	return min;

}

void sortArrays(int arr[],int arr1[], int n) {
	for (int i = 0; i < n; i++)
		for (int j = i+1; j < n - 1; j++)
			if (arr[i] > arr[j]) {
				int temp = arr[i];
				int temp1 = arr1[i];
				arr[i] = arr[j];
				arr1[i] = arr1[j];
				arr[j] = temp;
				arr1[j] = temp1;
			}
}
int main()
{
	int n, bursttime[20] = { 0 }, q1, q2, arrivaltime[20] = { 0 };

	cout << "enter no of processes; " << endl;
	cin >> n;

	cout << "enter the arrival time of each process: " << endl;
	for (int i = 0; i < n; i++)
	{
		cin >> arrivaltime[i];
	}

	int min_element_in_arrivaltime = min_element(arrivaltime, n);

	if (min_element_in_arrivaltime != 0)
	{
		for (int i = 0; i < n; i++)
		{
			arrivaltime[i] -= min_element_in_arrivaltime;
		}
	}

	cout << "enter the burst time of each process: " << endl;
	for (int i = 0; i < n; i++)
	{
		cin >> bursttime[i];
	}

	cout << "enter quantaum 1 and 2:" << endl;
	cin >> q1 >> q2;


	cout << endl << endl;
	sortArrays(arrivaltime, bursttime, n);
	operation(arrivaltime, bursttime, n, q1, q2);
	system("pause");
}

