package poder.ufac.br.projetointegrar.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Relogio {
	
	private Date data = new Date();
	boolean saida;
	
	public void saida(){
		saida = true;
	}
	
	public void entrada(){
		saida = false;
	}
	
	public boolean getSaida(){
		return saida;
	}
	
	public void setData(Date data){
		this.data = data;
	}
	public Date getDataHora(){
		return data = new Date();		
	}
	
	public String getHora(){	
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(data);	
	}
	public String getData(){	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
		return df.format(data);		
	}
	
	public String getDia(){	
		DateFormat df = new SimpleDateFormat("dd");		
		return df.format(data);		
	}
	
	public String getMes(){	
		DateFormat df = new SimpleDateFormat("MM");		
		return df.format(data);		
	}
	
	public String getAno(){	
		DateFormat df = new SimpleDateFormat("yyyy");		
		return df.format(data);		
	}
	
	public static String converteParaString(Date data){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(data);
	}
	
	public static Date dataStr(String h){
		Date dataStr = null;
		//String h = "12/03/2013";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//Calendar cal = null;	
		try {
			dataStr = df.parse(h);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataStr;
	}

	public static long zerarHoraLong(long l){
		return dataStr(converteParaString(new Date(l))).getTime();
	}

	public static Date zerarHoraDate(Date d){
		return dataStr(converteParaString(d));
	}

	public static Date dataStrHifem(String h){
		Date dataStr = null;
		//String h = "12/03/2013";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//Calendar cal = null;	
		try {
			dataStr = df.parse(h);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataStr;
	}
	
	public static Date zeraHorasData(Date data){
		Calendar zeraHora = Calendar.getInstance();   

		zeraHora.setTime(data);  
		
		//zerando hora do periodo inicial         
		zeraHora.set(Calendar.HOUR_OF_DAY, 0);  
		zeraHora.set(Calendar.MINUTE, 0);  
		zeraHora.set(Calendar.SECOND, 0);  
	      
	   return zeraHora.getTime();
	}
	
	public static Date horaMaximaData(Date data){
		Calendar maxHora = Calendar.getInstance();   

		maxHora.setTime(data);  
		
		//zerando hora do periodo inicial         
		maxHora.set(Calendar.HOUR_OF_DAY, 23);  
		maxHora.set(Calendar.MINUTE, 59);  
		maxHora.set(Calendar.SECOND, 59);  
	      
	   return maxHora.getTime();
	}
	public static boolean isSabadoOuDomingo(Date data) throws ParseException { 
		Calendar cal=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	    cal.setTime(data);  
	    int diaSemana = cal.get(Calendar.DAY_OF_WEEK);  
	    return diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY;  
	} 
	
	public static String getTextoMes(int mes){
		switch (mes) {
		case 1: return "Janeiro";
		case 2: return "Fevereiro";
		case 3: return "Março";
		case 4: return "Abril";
		case 5: return "Maio";
		case 6: return "Junho";
		case 7: return "Julho";
		case 8: return "Agosto";
		case 9: return "Setembro";
		case 10: return "Outubro";
		case 11: return "Novembro";
		case 12: return "Dezembro";	        	
		default: return "";
		}
	}
	
	public static String getTextoMes(String mes){
		int i = Integer.parseInt(mes);
		switch (i) {
		case 1: return "Janeiro";
		case 2: return "Fevereiro";
		case 3: return "Março";
		case 4: return "Abril";
		case 5: return "Maio";
		case 6: return "Junho";
		case 7: return "Julho";
		case 8: return "Agosto";
		case 9: return "Setembro";
		case 10: return "Outubro";
		case 11: return "Novembro";
		case 12: return "Dezembro";	        	
		default: return "";
		}
	}

	public Date stringEmHoras(String horaString){	
		
		Calendar horas = Calendar.getInstance();
				
		String [] arrayHora = horaString.split(":");    
        int h = Integer.parseInt(arrayHora[0]);    
        int m = Integer.parseInt(arrayHora[1]);
        int s = Integer.parseInt(arrayHora[2]);                                 
        
        horas.set(Calendar.HOUR_OF_DAY, h);
        horas.set(Calendar.MINUTE, m);
        horas.set(Calendar.SECOND, s);
        
		return horas.getTime();
	}
	
	public long stringEmSegundos(String horas){
		long tempo = 0;
		
		String [] arrayHora = horas.split(":");    
        int h = Integer.parseInt(arrayHora[0]);    
        int m = Integer.parseInt(arrayHora[1]);
        int s = Integer.parseInt(arrayHora[2]);          
                
        tempo = s + (m*60) + (h*3600);
        
        return tempo;
	}
	
    public String segundosEmTime(int segundos){ 
    	      
	    int segundo = segundos % 60;   
	    int minutos = segundos / 60;   
	    int minuto = minutos % 60;   
	    int hora = minutos / 60;   
	    String hms = String.format("%02d:%02d:%02d", hora, minuto, segundo);
	    return hms; // deve mostrar "HH:mm:ss"  
              
    }  
	
    public static Date getMesdoAno(int mes, int ano){
    	Calendar data = Calendar.getInstance();
    	switch (mes) {
			case 1 : data.set(Calendar.MONTH, Calendar.JANUARY); break;
			case 2 : data.set(Calendar.MONTH, Calendar.FEBRUARY); break;
			case 3 : data.set(Calendar.MONTH, Calendar.MARCH); break;
			case 4: data.set(Calendar.MONTH, Calendar.APRIL); break;
			case 5: data.set(Calendar.MONTH, Calendar.MAY); break;
			case 6: data.set(Calendar.MONTH, Calendar.JUNE); break;
			case 7: data.set(Calendar.MONTH, Calendar.JULY); break;
			case 8: data.set(Calendar.MONTH, Calendar.AUGUST); break;
			case 9: data.set(Calendar.MONTH, Calendar.SEPTEMBER); break;
			case 10: data.set(Calendar.MONTH, Calendar.OCTOBER); break;
			case 11: data.set(Calendar.MONTH, Calendar.NOVEMBER); break;
			case 12: data.set(Calendar.MONTH, Calendar.DECEMBER); break;
		default: break;
		}
    	
    	data.set(Calendar.YEAR, ano);
    	
    	return data.getTime();
    }
    
    public static String getMes(Date data){
    	SimpleDateFormat df = new SimpleDateFormat("MM");
    	int mes = Integer.parseInt(df.format(data));
    	switch (mes) {
			case 1: return "Janeiro";
			case 2: return "Fevereiro";
			case 3: return "Março";
			case 4: return "Abril";
			case 5: return "Maio";
			case 6: return "Junho";
			case 7: return "Julho";
			case 8: return "Agosto";
			case 9: return "Setembro";
			case 10: return "Outubro";
			case 11: return "Novembro";
			case 12: return "Dezembro";
		default : return null;
		}    	    	
    }
    
    public List<Date> Calendario(int mes, int ano) throws ParseException{  
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	List<Date> listaDiasMes = new ArrayList<Date>();
    	Calendar dataInicio = Calendar.getInstance();    	
    	dataInicio.set(Calendar.DAY_OF_MONTH, 1); 
    	dataInicio.set(Calendar.YEAR, ano); 
    	
    	Calendar dataFim = Calendar.getInstance();
    	dataFim.set(Calendar.DAY_OF_MONTH, 32);
    	dataFim.set(Calendar.YEAR, ano);    	
    	
    	switch (mes) {
		case 1:
	        dataInicio.set(Calendar.MONTH, Calendar.JANUARY);   	       
	        dataFim.set(Calendar.MONTH, Calendar.JANUARY);   
			break;
			
		case 2:
	        dataInicio.set(Calendar.MONTH, Calendar.FEBRUARY);   	       
	        dataFim.set(Calendar.MONTH, Calendar.FEBRUARY);   
			break;
			
		case 3:
	        dataInicio.set(Calendar.MONTH, Calendar.MARCH);   	       
	        dataFim.set(Calendar.MONTH, Calendar.MARCH);   
			break;
			
		case 4:
	        dataInicio.set(Calendar.MONTH, Calendar.APRIL);   	       
	        dataFim.set(Calendar.MONTH, Calendar.APRIL);   
			break;
			
		case 5:
	        dataInicio.set(Calendar.MONTH, Calendar.MAY);   	       
	        dataFim.set(Calendar.MONTH, Calendar.MAY);   
			break;
			
		case 6:
	        dataInicio.set(Calendar.MONTH, Calendar.JUNE);   	       
	        dataFim.set(Calendar.MONTH, Calendar.JUNE);   
			break;
			
		case 7:
	        dataInicio.set(Calendar.MONTH, Calendar.JULY);   	       
	        dataFim.set(Calendar.MONTH, Calendar.JULY);   
			break;
			
		case 8:
	        dataInicio.set(Calendar.MONTH, Calendar.AUGUST);   	       
	        dataFim.set(Calendar.MONTH, Calendar.AUGUST);   
			break;
			
		case 9:
	        dataInicio.set(Calendar.MONTH, Calendar.SEPTEMBER);   	       
	        dataFim.set(Calendar.MONTH, Calendar.SEPTEMBER);   
			break;
			
		case 10:
	        dataInicio.set(Calendar.MONTH, Calendar.OCTOBER);   	       
	        dataFim.set(Calendar.MONTH, Calendar.OCTOBER);   
			break;
			
		case 11:
	        dataInicio.set(Calendar.MONTH, Calendar.NOVEMBER);   	       
	        dataFim.set(Calendar.MONTH, Calendar.NOVEMBER);   
			break;
			
		case 12:
	        dataInicio.set(Calendar.MONTH, Calendar.DECEMBER);   	       
	        dataFim.set(Calendar.MONTH, Calendar.DECEMBER);   
			break;
								
		default:
			break;
		}
    	        
//        System.out.println(" data " + df.format(dataInicio.getTime()));  
//        System.out.println(" data fim " + df.format(dataFim.getTime()));  
          
        while (dataInicio.getTime().before(dataFim.getTime()) && (dataInicio.getTime().getMonth() == mes-1)){  
            if(isSabadoOuDomingo(dataInicio.getTime())){
            	//System.out.println(" Data " + df.format(dataInicio.getTime()) +"Fim de semana");
            	listaDiasMes.add(dataInicio.getTime());
            }else{
            	//System.out.println(" Data " + df.format(dataInicio.getTime()));
            	listaDiasMes.add(dataInicio.getTime());
            }
            dataInicio.set(Calendar.DAY_OF_MONTH, dataInicio.get(Calendar.DAY_OF_MONTH) +1 );  
        }
//        for(Date o : listaDiasMes){
//        	System.out.println(df.format(o));
//        }
		return listaDiasMes;  
          
          
    }  
    
    public void calendario2(){  
        Calendar calendar=Calendar.getInstance();  
        calendar.setTime(new Date());  
        for(int i=0;i<100;++i){  
            System.out.println(calendar.get(Calendar.DAY_OF_MONTH)+"/"+  
                    calendar.get(Calendar.MONTH)+"/"+  
                    calendar.get(Calendar.YEAR));  
            calendar.add(Calendar.DAY_OF_MONTH, 1);  
              
        }  
      
    } 
    
    public static Date anoParaData(int ano){
    	Calendar cal=Calendar.getInstance();
    	cal.set(Calendar.YEAR, ano);
    	return cal.getTime(); 
    }

}
