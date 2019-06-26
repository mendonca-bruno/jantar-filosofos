public class RC {
    int garfo[];
    int acess;
    
    public RC(){
        garfo = new int[6];
        acess = 0;
    }
    
    public synchronized int getAcess(){return this.acess;}
    
    public int tentaPegar(Processo p){
        
        if(p.id!=5) {if(garfo[p.id]!=1 && garfo[(p.id+1)]!=1)return 1; else return 0;}
        else {if(garfo[p.id]!=1 && garfo[1]!=1)return 1; else return 0;}
        
    }
    
    public void aloca(Processo p){
        if(p.id!=5){
            garfo[p.id]=1;
            garfo[(p.id+1)]=1;
        }else{
            garfo[p.id]=1;
            garfo[1]=1;
        }
        if(acess==0) acess++;
        else if(acess == 1) acess=0;
    }
 
    
    public void pegaGarfo(Processo p) throws InterruptedException{
        if(p.id!=5){
            System.out.println("Filosofo " +p.nome+" esta comendo. garfo "+p.id+" e "+(p.id+1));
            System.out.println();
        }else{
            System.out.println("Filosofo " +p.nome+" esta comendo. garfo "+p.id+" e "+1);
            System.out.println();
            
        }
    }
    
    public void liberarGarfos(Processo p){
        if(p.id!=5){
            garfo[p.id] = 0;
            garfo[(p.id+1)] = 0;
        }else{
            garfo[p.id] = 0;
            garfo[1] = 0;
        }
    }
    
    public int allClear(){
        int aux = 0;
        for(int i=0; i<6; i++){
            if(garfo[i]==0) aux++;
        }
        if (aux==6) return 1;
        else return 0;
    }

}
