func addToArrayForm(A []int, K int) []int {
    carry:=0
    digK:=0
    res:=[]int{}      
    for i:=len(A)-1;i>=0;i-- {
        digK=K%10
        K=K/10
        res=append(res,(A[i]+digK+carry)%10)
        carry=(A[i]+digK+carry)/10
    }

     for K>0 {
		digK=K%10
		K=K/10		
        res=append(res,(digK+carry)%10)
		carry=(digK+carry)/10		
    }
    if carry>0 {
        res=append(res,carry)
    } 
    for i:=0;i<len(res)/2;i++ {
        tmp:=res[i]
        res[i]=res[len(res)-1-i]
        res[len(res)-1-i]=tmp
    }    
    return res        
}
