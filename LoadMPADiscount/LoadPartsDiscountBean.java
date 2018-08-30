import com.siebel.data.*;
import com.siebel.data.SiebelException;
import java.util.List;


public class LoadPartsDiscountBean
{
   private SiebelDataBean 	m_dataBean = null;
   private SiebelBusObject  m_busObject = null;
   private SiebelBusComp    m_busComp_MPA = null;
   private SiebelBusComp    m_busComp_Plan = null;
   private SiebelBusComp    m_busComp_Parts = null;
   private Boolean 			bRecordFoundMPA = false;
   private Boolean 			bRecordFoundPlan = false;
   
   private String[] methodArgs = {"TRUE"};	

   public static void main(String[] args)
   {
	  List<String[]> recs = ProcessFile.readFile("mpa_parts.csv");
      LoadPartsDiscountBean demo = new LoadPartsDiscountBean(recs);
   }

   public LoadPartsDiscountBean(List<String[]> records)
   {
      try
      {
         // instantiate the Siebel Java Data Bean
         m_dataBean = new SiebelDataBean();

         // log in to the Siebel Server
         // SiebelServerhost = the name or IP address of your Siebel Server
         // SCBPort = listening port number for the SCBroker component (default 2321)
         m_dataBean.login("Siebel://de08w2145:2321/DEV2_ENT/SFSObjMgrAdmin_enu", "SADMIN", "siebadm15tr", "enu");

         // get the business object
         m_busObject = m_dataBean.getBusObject("HW MPA System Agreements");

         // get the business component
         m_busComp_Parts = m_busObject.getBusComp("HW MPA Pricing Plan Details - COM");


		 m_busComp_Parts.invokeMethod("SetAdminMode", methodArgs);
		 m_busComp_Parts.clearToQuery();
		 m_busComp_Parts.executeQuery(false);

		 for (String[] record : records) {
				 try{
					m_busComp_Parts.newRecord(false);
					m_busComp_Parts.setFieldValue("Pricing Plan Id", record[0]);
					m_busComp_Parts.setFieldValue("Product Id", record[1]);
					m_busComp_Parts.setFieldValue("System Discount", record[2]);
					m_busComp_Parts.setFieldValue("Type", "PD");
					m_busComp_Parts.writeRecord();
					
					System.out.println("# Parts Discount Created. " + m_busComp_Parts.getFieldValue("Id"));
				 }
				 catch(SiebelException e){
					 System.out.println("# Error while inserting new record. Pricing Plan Id:" + record[0] + " Product Id:"+ record[1] + " System Discount:"+ record[2]);
				 }
		 }
         // log off
         m_dataBean.logoff();
      }

      catch (SiebelException e)
      {
         System.out.println(e.getErrorMessage());
      }
   }
}