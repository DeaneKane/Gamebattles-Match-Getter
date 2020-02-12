package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class LoginDecryption
{

    private String passwordForFile = "javaPass#";
    private String password;
    private String userName;
    private String readFile;
    private FileInputStream inFile;
    private File file = new File("plainfile_decrypted.txt");

    public LoginDecryption() throws Exception
    {
        decryptFile();
        readFileContents();
    }


    public void decryptFile() throws Exception
    {

        PBEKeySpec pbeKeySpec = new PBEKeySpec( passwordForFile.toCharArray() );
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance( "PBEWithMD5AndTripleDES" );
        SecretKey secretKey = secretKeyFactory.generateSecret( pbeKeySpec );

        FileInputStream fis = new FileInputStream( "configurations.des" );
        byte[] salt = new byte[8];
        fis.read( salt );

        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec( salt, 100 );

        Cipher cipher = Cipher.getInstance( "PBEWithMD5AndTripleDES" );
        cipher.init( Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec );
        FileOutputStream fos = new FileOutputStream( "plainfile_decrypted.txt" );
        byte[] in = new byte[64];
        int read;
        while( (read = fis.read( in )) != -1 )
        {
            byte[] output = cipher.update( in, 0, read );
            if( output != null )
                fos.write( output );
        }

        byte[] output = cipher.doFinal();
        if( output != null )
            fos.write( output );

        fis.close();
        fos.flush();
        fos.close();
    }
    
    public void readFileContents() throws IOException {
        
        inFile = new FileInputStream( "plainfile_decrypted.txt" );
        byte b[]=new byte[inFile.available()]; 
        inFile.read(b);
        readFile = new String(b);
        inFile.close();
        
    }
    
    public String extractUsername() throws IOException {
                
        userName = readFile.substring( 0,  readFile.indexOf( '#' ) );
        return userName;
    }
    
    public String extractPassword() throws IOException {
        
        password = readFile.substring( readFile.indexOf( '#' ) + 1);
        return password;
    }
    
    public void deletePlainTextFile() {
        file.delete();
    }
}
