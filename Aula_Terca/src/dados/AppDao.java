package dados;


import java.sql.*;

public class AppDao {

    Connection conectado;
    PreparedStatement st;

    private void conectar() throws ClassNotFoundException, SQLException{
        // 1 passo conectar com o banco de dados 
        Class.forName("com.mysql.cj.jdbc.Driver");
        conectado = DriverManager.getConnection("jdbc:mysql://localhost:3307/empresa", "root", "p@$$w0rd");

    }

    public void salvarUsuario(String u, String s, String c) throws ClassNotFoundException, SQLException{
        conectar();
        st = conectado.prepareStatement("INSERT INTO usuarios values (?,?,?)");
        st.setString(1, u);
        st.setString(2, s);
        st.setString(3, c);
        st.executeUpdate(); //salva no bdc 
    }
    
    public ResultSet entrar(String u, String s) throws ClassNotFoundException, SQLException{
        conectar();
        st = conectado.prepareStatement("select * from usuarios where usuario= ? and senha= ?");
        st.setString(1, u);
        st.setString(2, s);
        ResultSet resultado = st.executeQuery(); // Executa o select (busca) 
        return resultado;
    }
    
    public int excluirUsuario(String u) throws ClassNotFoundException, SQLException{
        conectar();
        st = conectado.prepareStatement("DELETE FROM usuarios Where usuario = ?");
        st.setString(1, u);
        int retorno = st.executeUpdate();
        return retorno;
    }
    
    public void alterarUsuario(String u, String s, String c) throws ClassNotFoundException, SQLException{
        conectar();
        PreparedStatement st = conectado.prepareStatement("UPDATE usuarios SET senha=?, cargo=? Where usuarios=?");
        st.setString(1, s);
        st.setString(2, c);
        st.setString(3, u); 
        st.executeUpdate();

    }
    
    public ResultSet buscarUsuarios(String u) throws SQLException, ClassNotFoundException{
        conectar();
        PreparedStatement st = conectado.prepareStatement("select * FROM usuarios Where usuario = ?");
        st.setString(1, u);
        ResultSet usuario = st.executeQuery(); //EXECUTA O SELECT 
        return  usuario;
        
    }
    public ResultSet listarUsuarios(String u) throws ClassNotFoundException, SQLException{
        conectar();
        
        
    }

}

