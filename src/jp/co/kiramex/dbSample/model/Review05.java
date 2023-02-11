package jp.co.kiramex.dbSample.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        
            // 3. データベース接続と結果取得のための変数宣言
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // 1.ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.DBと接続する
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "Arumatan9219"
                
            );
            
            // 4.DBとやりとりする窓口　(Statementオブジェクト) の作成
            String sql = "SELECT * FROM person WHERE id = ?"; // ←　修正
            pstmt = con.prepareStatement(sql);
            // 5,6 Select文の実行と結果を格納/代入
            System.out.print("検索キーワードを入力してください　＞"); // ←　追記
            int input = keyInNum();   // ←　追記
            
            // PreparedStatementオブジェクトの？に値をセット　// ←　追記
            pstmt.setInt(1, input); // ←　追記
            rs = pstmt.executeQuery();
            // 7. 結果を表示する
            while (rs.next() ) {
                // Name列の値を取得
                String name = rs.getString("name");
                // Population列の値を取得　←　追記
                int age = rs.getInt("age"); // ←　追記
                // 取得した値を表示
                System.out.println(name);
                System.out.println(age); // ←　追記
            }
         } catch (ClassNotFoundException e) {
            System.err.println("JDCBドライバのロードに失敗しました");
            e.printStackTrace();
         }catch (SQLException e) {
            System.err.println("データベースに異常が発見しました");
            e.printStackTrace();
         }finally {
            // 8. 接続を閉じる
             if (rs != null) {
                 try {
                     rs.close();
                 }catch (SQLException e) {
                     System.err.println("ResultSetを閉じるときにエラーが発生しました");
                     e.printStackTrace();
                 }
             }
             if (pstmt != null) {
                 try {
                     pstmt.close();
                 }catch (SQLException e) {
                     System.out.println("PreparedStatementを閉じるときにエラーが発生しました");
                     e.printStackTrace();
                 }
             }
             if (con != null) {
                 try {
                     con.close();
                 }catch (SQLException e) {
                     System.err.println("データベース接続時にエラーが発生しました");
                     e.printStackTrace();
                 }
             }
             
 
         }
        
    }
    /*
     * キーボードから入力された値をStringで返す　引数：なし　戻り値：入力された文字列　// ←　追記
     */
    private static String keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        }catch (IOException e) {
            
        }
        return line;
    }
    
    private static int keyInNum() {
        int result = 0;
        try {
            result = Integer.parseInt(keyIn());
        }catch (NumberFormatException e) {
            
        }
        return result;
    }
}
