#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <string>
#include <fstream>

using namespace std;

#define MAXN 65536
#define MAXLG 17

string A;

struct entry
{
    int nr[2];
    int p;
} L[MAXN];

int P[MAXLG][MAXN];
int N,i;
int stp, cnt;

int cmp(struct entry a, struct entry b)
{
    return a.nr[0]==b.nr[0] ?(a.nr[1]<b.nr[1] ?1: 0): (a.nr[0]<b.nr[0] ?1: 0);
}


vector<int> kasai(string s, vector<int> sa)
{
    int n=s.size(),k=0;
    vector<int> lcp(n,0);
    vector<int> rank(n,0);

    for(int i=0; i<n; i++) rank[sa[i]]=i;

    for(int i=0; i<n; i++, k?k--:0)
    {
        if(rank[i]==n-1) {
            k=0; 
            continue;
        }
        int j=sa[rank[i]+1];
        while(i+k<n && j+k<n && s[i+k]==s[j+k]) k++;
        lcp[rank[i]]=k;
    }
    return lcp;
}

double computeDg(string g, vector<int> S, vector<int> L)
{
    double N = g.length();
    double ans =0 ;
    for (i=0;i<N;i++)
    {
        ans = ans + N  - S[i] + 1 - L[i];
    }

    ans = ans/(N * (N+1));

    return 2*ans;

}

double computeDgK(string g, vector<int> S, vector<int> L, int k)
{
    double ans=0;
    double N = g.length();
    for (i=0;i<N;i++)
    {
        if ((S[i]<=N -k +1) && L[i]<k)
        {
            ans = ans + 1;
        }
    }
    ans = ans/ (N - k +1);
    return ans;
}

double computeRgK(string g, vector<int> L, int k)
{
    int N = g.length();
    int i,j;
    int count[N];
    double ans=0;

    if (L[0]>=k)
        count[0]=0;
    else
        count[0]=1;

    for (i=1;i<N;i++)
    {
        if (L[i]>=k)
        {
            count[i] = count[i-1]+1;
        }
    }

    for (i=0;i<N;i++)
    {
        for (j=i;j<N;j++)
        {
            if (count[j] > count[i] || (i==0 && count[0]==1) || (i==j && count[i]>count[i-1]))
            {
                if ((i==0 || count[i-1]<k) && (j==N-1 || count[j+1]<k))
                {
                    ans = ans+j - i + 2;
                }
            }
        }
    }

    ans = ans/(N - k +1);

    return ans;


}



int main(int argc, char *argv[])
{
    //cin >> A;

    ofstream myfile;
    myfile.open ("periodicity.csv");
    myfile << "Dg,DgK,RgK\n";
    ifstream infile(argv[1]);
    string line;
    int count=0;
    while (getline(infile, line))
    {
        if (count%2==0)
        {
            count++;
            
        }
        else
        {
        string A = line;
        //cout << "Reached here";
    
    for(N=A.length(), i = 0; i < N; i++)
        P[0][i] = A[i] - 'a';

    for(stp=1, cnt = 1; cnt < N; stp++, cnt *= 2)
    {
        for(i=0; i < N; i++)
        {
            L[i].nr[0]=P[stp- 1][i];
            L[i].nr[1]=i +cnt <N? P[stp -1][i+ cnt]:-1;
            L[i].p= i;
        }
        sort(L, L+N, cmp);
        for(i=0; i < N; i++)
            { 
                P[stp][L[i].p] =i> 0 && L[i].nr[0]==L[i-1].nr[0] && L[i].nr[1] == L[i- 1].nr[1] ? P[stp][L[i-1].p] : i;
                
            }
    }


    



    vector<int> sa;

    for (i=0;i<N;i++)
    {
        
        sa.push_back(L[i].p); 
    } 

    vector<int> lcp;
    //cout << "suffix array computed " << endl;
    lcp = kasai(A,sa);

    lcp.pop_back();
    lcp.insert(lcp.begin(),0);

     
    
    for (i=0;i<N;i++)
    {
        sa[i] = sa[i]+1;
      //  cout << lcp[i] << endl;
    }

    /*cout << "Suffix array is " << endl;

    for (i=0;i<N;i++)
    {
        cout << sa[i] << endl;
    }*/

    int k=2;
    
    myfile << computeDg(A,sa,lcp) << "," << computeDgK(A,sa,lcp,k) << "," << computeRgK(A,lcp,k) << "\n";
    /*cout << "Dg is " << computeDg(A,sa,lcp) << endl;
    
    cout << "DgK is " << computeDgK(A,sa,lcp,k) << endl;

    cout << "RgK is " << computeRgK(A,lcp,k) << endl;*/
    count++;
}

}
myfile.close();
    return 0;
}
