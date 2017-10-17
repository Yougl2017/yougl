package com.dao.conn;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.swing.JOptionPane;

import com.dao.bean.Company;
import com.dao.bean.Goods;
import com.dao.bean.Sale;
import com.dao.bean.TempSaleDetail;
import com.other.Item;


public class Dao {
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String dbUrl = "jdbc:sqlserver://localhost:1434;databaseName=hhbl";
			
	protected static String dbUser = "sa";
	protected static String dbPwd = "sa";
	protected static String second = null;
	public static Connection conn = null;
	static {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"�뽫SQL Server 2000��JDBC���������Ƶ�lib�ļ����С�");
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Dao() {
	}
	
	public static int restoreOrBackup(String sql) throws Exception {
		int rs = 0;
		if (conn != null) {
			conn.close();
		}
		// ���ӵ�ϵͳ���ݿ�
		conn = DriverManager.getConnection(
				"jdbc:jtds:sqlserver://localhost:1434/master", dbUser,
				dbPwd);
		Statement stmt = conn.createStatement();
		// ���嵥�û�����ģʽ��SQL���
		String single = "alter database hhbl set single_user"
				+ " with rollback immediate " + sql;
		// ִ�����ݿⱸ�ݻ�ָ���SQL���
		rs = stmt.executeUpdate(single);
		stmt.close();
		conn.close(); // �ر����ݿ�����
		// �ָ�ԭ�����ݿ�����
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		return rs;
	}
	
	// 客户信息
	public static List getKhInfos() {
		List list = findForList("select company_id,company_name from company");
		return list;
	}
	//获取商品信息
	public static List getspInfos(){
		List list = findForList("select goods_id,Goods_name "
				+ "from goods where Goods_isusable=0 ");
		return list;
		
	}
	//获取客户信息
	public static Company getkhInfo(Item item){
		String where = "Company_Name='"+item.getName()+"'";
		if (item.getId()!=null){
			where = "company_id='"+item.getId()+"'";
		}
		Company info =new Company();
		ResultSet set = findForResultSet("select company_id,company_name,company_address,company_contacts,company_phone,company_ramark from company where "+where);
		try {
			if (set.next()){
				info.setCompanyId(set.getString("company_id").trim());
				info.setCompanyName(set.getString("company_name").trim());
				info.setCompanyAddress(set.getString("company_address").trim());
				info.setCompanyContacts(set.getString("company_contacts").trim());
				info.setCompanyPhone(set.getString("company_phone").trim());
				info.setCompanyRamark(set.getString("company_ramark").trim());
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return info;
		
	}
	//获取商品信息
		public static Goods getspInfo(Item item){
			String where = "Goods_Name='"+item.getName()+"'";
			if (item.getId()!=null){
				where = "Goods_id='"+item.getId()+"'";
			}
			Goods info =new Goods();
			ResultSet set = findForResultSet("select Goods_id,goods_name,goods_price,goods_typeid,goods_type,goods_isusable,goods_remark from goods where "+where);
			try {
				if (set.next()){
					info.setGoodsId(set.getString("Goods_id").trim());
					info.setGoodsName(set.getString("goods_name").trim());
					info.setGoodsPrice(set.getString("goods_price").trim());
					info.setGoodsTypeid(set.getString("goods_typeid").trim());
					info.setGoodsIsusable(set.getString("goods_isusable"));
					info.setGoodsRemark(set.getString("company_ramark").trim());
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return info;
			
		}
	

	
	//指定查询ִ
	public static ResultSet query(String QueryStr) {
		ResultSet set = findForResultSet(QueryStr);
		return set;
	}
	
	//插入销售商品
	public static boolean addSaleDetail(TempSaleDetail saledetail){
		if (saledetail==null){
			return false;
		}
		return insert("insert temp_sale_detail(zblsh,idm,dname,dw,dj,chang,kuang,gg,sl,mblx,mbdj,mbzc,zklx,zkdj,zksl,kklx,kkdj,kksl,zje,bz) values('"
				      + saledetail.getZblsh()+"','"
				      + saledetail.getIdm()+"','"
				      + saledetail.getDname()+"','"
				      + saledetail.getDw()+"','"
				      + saledetail.getDj()+"','" 
				      + saledetail.getChang()+"','"
				      + saledetail.getKuang()+"','"
				      + saledetail.getGg()+"','"
				      + saledetail.getSl()+"','"
				      + saledetail.getMblx()+"','"
				      + saledetail.getMbdj()+"','"
				      + saledetail.getMbzc()+"','"
				      + saledetail.getZklx()+"','"
				      + saledetail.getZkdj()+"','"
				      + saledetail.getZksl()+"','"
				      + saledetail.getKklx()+"','"
				      + saledetail.getKkdj()+"','"
				      + saledetail.getKksl()+"','"
				      + saledetail.getZje()+"','"
				      + saledetail.getBz()+"')"
				      
				);
		
	}
	public static boolean addSale(Sale sale){
		if (sale==null){
			return false;
		}
		
		return exec("{call usp_print_action(?,?,?,?,?)}",sale.getLsh(),sale.getSgs(),sale.getName(),sale.getSphone(),sale.getAddress());
		
	}
/*	
	// ִ��ɾ��
	public static int delete(String sql) {
		return update(sql);
	}
	
	// 添加客户信息
	public static boolean addKeHu(TbKhinfo khinfo) {
		if (khinfo == null)
			return false;
		return insert("insert tb_khinfo values('" + khinfo.getId() + "','"
				+ khinfo.getKhname() + "','" + khinfo.getJian() + "','"
				+ khinfo.getAddress() + "','" + khinfo.getBianma() + "','"
				+ khinfo.getTel() + "','" + khinfo.getFax() + "','"
				+ khinfo.getLian() + "','" + khinfo.getLtel() + "','"
				+ khinfo.getMail() + "','" + khinfo.getXinhang() + "','"
				+ khinfo.getHao() + "')");
	}
	
	// �޸Ŀͻ���Ϣ�ķ���
	public static int updateKeHu(TbKhinfo khinfo) {
		return update("update tb_khinfo set jian='" + khinfo.getJian()
				+ "',address='" + khinfo.getAddress() + "',bianma='"
				+ khinfo.getBianma() + "',tel='" + khinfo.getTel()
				+ "',fax='" + khinfo.getFax() + "',lian='"
				+ khinfo.getLian() + "',ltel='" + khinfo.getLtel()
				+ "',mail='" + khinfo.getMail() + "',xinhang='"
				+ khinfo.getXinhang() + "',hao='" + khinfo.getHao()
				+ "' where id='" + khinfo.getId() + "'");
	}
	
	// �޸Ŀ��ķ���
	public static int updateKucunDj(TbKucun kcInfo) {
		return update("update tb_kucun set dj=" + kcInfo.getDj()
				+ " where id='" + kcInfo.getId() + "'");
	}
	
	// �޸Ĺ�Ӧ����Ϣ�ķ���
	public static int updateGys(TbGysinfo gysInfo) {
		return update("update tb_gysinfo set jc='" + gysInfo.getJc()
				+ "',address='" + gysInfo.getAddress() + "',bianma='"
				+ gysInfo.getBianma() + "',tel='" + gysInfo.getTel()
				+ "',fax='" + gysInfo.getFax() + "',lian='"
				+ gysInfo.getLian() + "',ltel='" + gysInfo.getLtel()
				+ "',mail='" + gysInfo.getMail() + "',yh='"
				+ gysInfo.getYh() + "' where id='" + gysInfo.getId() + "'");
	}
	
	// ��ӹ�Ӧ����Ϣ�ķ���
	public static boolean addGys(TbGysinfo gysInfo) {
		if (gysInfo == null)
			return false;
		return insert("insert tb_gysinfo values('" + gysInfo.getId()
				+ "','" + gysInfo.getName() + "','" + gysInfo.getJc()
				+ "','" + gysInfo.getAddress() + "','"
				+ gysInfo.getBianma() + "','" + gysInfo.getTel() + "','"
				+ gysInfo.getFax() + "','" + gysInfo.getLian() + "','"
				+ gysInfo.getLtel() + "','" + gysInfo.getMail() + "','"
				+ gysInfo.getYh() + "')");
	}
	
	// �����Ʒ
	public static boolean addSp(TbSpinfo spInfo) {
		if (spInfo == null)
			return false;
		return insert("insert tb_spinfo values('" + spInfo.getId() + "','"
				+ spInfo.getSpname() + "','" + spInfo.getJc() + "','"
				+ spInfo.getCd() + "','" + spInfo.getDw() + "','"
				+ spInfo.getGg() + "','" + spInfo.getBz() + "','"
				+ spInfo.getPh() + "','" + spInfo.getPzwh() + "','"
				+ spInfo.getMemo() + "','" + spInfo.getGysname() + "')");
	}
	
	// ������Ʒ
	public static int updateSp(TbSpinfo spInfo) {
		return update("update tb_spinfo set jc='" + spInfo.getJc()
				+ "',cd='" + spInfo.getCd() + "',dw='" + spInfo.getDw()
				+ "',gg='" + spInfo.getGg() + "',bz='" + spInfo.getBz()
				+ "',ph='" + spInfo.getPh() + "',pzwh='"
				+ spInfo.getPzwh() + "',memo='" + spInfo.getMemo()
				+ "',gysname='" + spInfo.getGysname() + "' where id='"
				+ spInfo.getId() + "'");
	}
	
	// ��ȡ������Ʒ��Ϣ
	public static List getSpInfos() {
		List list = findForList("select * from tb_spinfo");
		return list;
	}
	
	
	
	// ����������������Ϣ
	public static boolean insertRukuInfo(TbRukuMain ruMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// �����������¼
			insert("insert into tb_ruku_main values('" + ruMain.getRkId()
					+ "','" + ruMain.getPzs() + "'," + ruMain.getJe()
					+ ",'" + ruMain.getYsjl() + "','"
					+ ruMain.getGysname() + "','" + ruMain.getRkdate()
					+ "','" + ruMain.getCzy() + "','" + ruMain.getJsr()
					+ "','" + ruMain.getJsfs() + "')");
			Set<TbRukuDetail> rkDetails = ruMain.getTabRukuDetails();
			for (Iterator<TbRukuDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				TbRukuDetail details = iter.next();
				// ��������ϸ���¼
				insert("insert into tb_ruku_detail values('"
						+ ruMain.getRkId() + "','"
						+ details.getTabSpinfo() + "'," + details.getDj()
						+ "," + details.getSl() + ")");
				// ��ӻ��޸Ŀ����¼
				Item item = new Item();
				item.setId(details.getTabSpinfo());
				TbSpinfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					TbKucun kucun = getKucun(item);
					if (kucun.getId() == null || kucun.getId().isEmpty()) {
						insert("insert into tb_kucun values('"
								+ spInfo.getId() + "','"
								+ spInfo.getSpname() + "','"
								+ spInfo.getJc() + "','" + spInfo.getCd()
								+ "','" + spInfo.getGg() + "','"
								+ spInfo.getBz() + "','" + spInfo.getDw()
								+ "'," + details.getDj() + ","
								+ details.getSl() + ")");
					} else {
						int sl = kucun.getKcsl() + details.getSl();
						update("update tb_kucun set kcsl=" + sl + ",dj="
								+ details.getDj() + " where id='"
								+ kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	*/
	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;
		long time = System.currentTimeMillis();
		ResultSet rs = null;
		try {
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			second = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static boolean insert(String sql) {
		boolean result = false;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean exec(String sql,String zblsh,String sgs,String sname,String sphone,String address){
		boolean result = false;
		try{
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1,zblsh);
			cstmt.setString(2,sgs);
			cstmt.setString(3, sname);
			cstmt.setString(4, sphone);
			cstmt.setString(5, address);
			cstmt.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public static int update(String sql) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List findForList(String sql) {
		List<List> list = new ArrayList<List>();
		ResultSet rs = findForResultSet(sql);
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//获得最大ID
	public static String getSellMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "Sale", "HH", "lsh");
	}
	//获取明细最大Id
	public static String getTDedailMaxid(){
		return getDedailMaxId();
		
	}
/*	
	// �����������������Ϣ
	public static boolean insertSellInfo(TbSellMain sellMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// ������������¼
			insert("insert into tb_sell_main values('"
					+ sellMain.getSellId() + "','" + sellMain.getPzs()
					+ "'," + sellMain.getJe() + ",'" + sellMain.getYsjl()
					+ "','" + sellMain.getKhname() + "','"
					+ sellMain.getXsdate() + "','" + sellMain.getCzy()
					+ "','" + sellMain.getJsr() + "','"
					+ sellMain.getJsfs() + "')");
			Set<TbSellDetail> rkDetails = sellMain.getTbSellDetails();
			for (Iterator<TbSellDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				TbSellDetail details = iter.next();
				// ���������ϸ���¼
				insert("insert into tb_sell_detail values('"
						+ sellMain.getSellId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl()
						+ ")");
				// �޸Ŀ����¼
				Item item = new Item();
				item.setId(details.getSpid());
				TbSpinfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					TbKucun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() - details.getSl();
						update("update tb_kucun set kcsl=" + sl
								+ " where id='" + kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	*/
	// 获得最大ID
	private static String getMainTypeTableMaxId(Date date, String table,
			String idChar, String idName) {
		String dateStr = date.toString().replace("-", "");
		String id = idChar + dateStr;
		String sql = "select max(" + idName + ") from " + table
				+ " where " + idName + " like '" + id + "%'";
		ResultSet set = query(sql);
		String baseId = null;
		try {
			if (set.next())
				baseId = set.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		baseId = baseId == null ? "000" : baseId
				.substring(baseId.length() - 3);
		int idNum = Integer.parseInt(baseId) + 1;
		id += String.format("%03d", idNum);
		return id;
	}
	//获取明细最大ID
 
	private static String getDedailMaxId(){
    	String maxId=null;
    	String sql = "select max(mxxh)+1 from temp_sale_detail";
    	ResultSet set = query(sql);
    	try {
    		if(set.next()){
    			maxId=set.getString(1);
    		}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return maxId;
    }
	
	/*打印信息*/

	/*
	public static String getXsthMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_xsth_main", "XT", "xsthID");
	}
	
	public static List getKucunInfos() {
		List list = findForList("select id,spname,dj,kcsl from tb_kucun");
		return list;
	}
	
	// ����������������˻���Ϣ
	public static boolean insertXsthInfo(TbXsthMain xsthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// ��������˻������¼
			insert("insert into tb_xsth_main values('"
					+ xsthMain.getXsthId() + "','" + xsthMain.getPzs()
					+ "'," + xsthMain.getJe() + ",'" + xsthMain.getYsjl()
					+ "','" + xsthMain.getKhname() + "','"
					+ xsthMain.getThdate() + "','" + xsthMain.getCzy()
					+ "','" + xsthMain.getJsr() + "','"
					+ xsthMain.getJsfs() + "')");
			Set<TbXsthDetail> xsthDetails = xsthMain.getTbXsthDetails();
			for (Iterator<TbXsthDetail> iter = xsthDetails.iterator(); iter
					.hasNext();) {
				TbXsthDetail details = iter.next();
				// ��������˻���ϸ���¼
				insert("insert into tb_xsth_detail values('"
						+ xsthMain.getXsthId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl()
						+ ")");
				// �޸Ŀ����¼
				Item item = new Item();
				item.setId(details.getSpid());
				TbSpinfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					TbKucun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() + details.getSl();
						update("update tb_kucun set kcsl=" + sl
								+ " where id='" + kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	// ����û�
	public static int addJsr(TbJsr jsr) {
		String sql = "insert tb_jsr values('" + jsr.getName() + "','"
				+ jsr.getSex() + "','" + jsr.getAge() + "','"
				+ jsr.getTel() + "',1)";
		System.out.println(sql);
		return update(sql);
	}
	
	public static List getJsrs() {
		List list = findForList("select * from tb_jsr where enable=1");
		return list;
	}*/

}