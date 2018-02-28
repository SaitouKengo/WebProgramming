package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utill.Utill;


public class UserDao {

	//ログインIDとパスワードに紐づくユーザ情報を返す

    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2,Utill.convertmd5(password));
            ResultSet rs = pStmt.executeQuery();


             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public User findByUserInfo(String detailId) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, detailId);




            ResultSet rs = pStmt.executeQuery();


             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            Date birthDateData = rs.getDate("birth_date");
            String createDateData = rs.getString("create_date");
            String passwordData  = rs.getString("password");
            String updateDateData = rs.getString("update_date");




            return new User(Integer.parseInt(detailId) , loginIdData, nameData, birthDateData,passwordData, createDateData,updateDateData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    //削除用メソッド
	public boolean userDelete(String id)  {
		//DB接続
		Connection conn = DBManager.getConnection();
		try {

			//sql送信
			PreparedStatement st = conn.prepareStatement("DELETE FROM user WHERE id = ?");
			st.setString(1,id);

			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
	    } finally {
	        // データベース切断
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

		return false;

    }



     //全てのユーザ情報を取得する


    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE login_id NOT IN ('admin')";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }

    //検索用
    public List<User> findSearch(String loginId, String userName, String birthDate) {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {

            conn = DBManager.getConnection();


            String sql = "SELECT * FROM user WHERE login_id NOT IN ('admin') ";

            if(!loginId.equals("")) {
            	sql += " and login_id = '" + loginId + "'";
            }

            if(!userName.equals("")) {
            	sql += " and name LIKE '" + '%' + userName + '%' + "'";
            }

            if(!birthDate.equals("")) {
            	sql += " and birth_date = '" + birthDate + "'";
            }

            System.out.println(sql);



            // SELECTを実行し、結果表を取得
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql);

           // 結果表に格納されたレコードの内容を
           // Userインスタンスに設定し、ArrayListインスタンスに追加
           while (rs.next()) {
               int id = rs.getInt("id");
               String loginIdData = rs.getString("login_id");
               String name = rs.getString("name");
               Date birthDateData = rs.getDate("birth_date");
               String password = rs.getString("password");
               String createDate = rs.getString("create_date");
               String updateDate = rs.getString("update_date");
               User user = new User(id, loginIdData, name, birthDateData, password, createDate, updateDate);

               userList.add(user);
           }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }


        }

        return userList;

    }

    	//更新用メソッド
    public static boolean userUpdate(String id, String loginId,String pass,String passConfirm,String name,String birthDate) {
		Connection conn = null;
		try {
		conn = DBManager.getConnection();

		//未入力チェック
		if(loginId.equals("")||name.equals("")||birthDate.equals("")) {
			return false;
		}

		//入力が正しいかのチェック
		if(!(pass.equals(passConfirm))||!(birthDate.length()==10)) {
			return false;
		}



		if(pass.equals("")&&passConfirm.equals("")) {
			String sql = "UPDATE user SET login_id = ?, name = ?, birth_date = ? WHERE id = ? ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, loginId);
	        pStmt.setString(2, name);
	        pStmt.setString(3, birthDate);
	        pStmt.setString(4, id);

	        pStmt.executeUpdate();

	        return true;

		}else {

		String sql = "UPDATE user SET login_id = ?, name = ?, birth_date = ?, password = ? WHERE id = ? ";



		PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, loginId);
        pStmt.setString(2, name);
        pStmt.setString(3, birthDate);
        pStmt.setString(4, Utill.convertmd5(pass));
        pStmt.setString(5, id);

        pStmt.executeUpdate();

		return true;
		}

	}catch(SQLException e) {
		e.printStackTrace();
		return false;
	}finally {
		//データベース切断
		if(conn!=null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

    }

    //新規登録用メソッド
	public static boolean userInsert(String loginId,String pass,String passConfirm,String name,String birthDate) {
		Connection conn = null;
	try {
		conn = DBManager.getConnection();

		//未入力チェック
		if(loginId==""||pass==""||passConfirm==""||name==""||birthDate=="") {
			return false;
		}

		//入力が正しいかのチェック
		if(!(pass.equals(passConfirm))||!(birthDate.length()==10)) {
			return false;
		}


		String sql = "INSERT INTO user(login_id, name, birth_date, password,  create_date, update_date) VALUES (?, ?, ?, ?, NOW(), NOW())";

		PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, loginId);
        pStmt.setString(2, name);
        pStmt.setString(3, birthDate);
        pStmt.setString(4, Utill.convertmd5(pass));


        pStmt.executeUpdate();

		return true;

	}catch(SQLException e) {
		e.printStackTrace();
		return false;
	}finally {
		//データベース切断
		if(conn!=null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
    }
}

	public User loginIdData(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


}