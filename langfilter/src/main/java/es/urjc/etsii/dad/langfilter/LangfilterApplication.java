package es.urjc.etsii.dad.langfilter;

import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.urjc.etsii.dad.langfilter.thrift.CrossPlatformServiceServer;
import java.io.*;

@SpringBootApplication
public class LangfilterApplication {

	public static void main(String[] args) throws TTransportException {
		SpringApplication.run(LangfilterApplication.class, args);

		CrossPlatformServiceServer server = new CrossPlatformServiceServer();
        server.start();
    System.out.println(filter("Chúpamela miserable bizco"));
  }
  
	private static String fileToString(String _path)
	{
		try
		{
			FileReader fr = new FileReader("langfilter/src/main/resources/Dictionary.txt");
			BufferedReader br = new BufferedReader(fr);
			String _line = "";
			String _lines = "";
			while(_line != null)
			{
				_line = br.readLine();
				if(_line != null) _lines += _line + "\n";
			}
			br.close();
			return _lines;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public static String filter(String _sentence)
	{
		String[] _dictionary = fileToString("langfilter/src/resources/Dictionary.txt").split("\n");
		//PrintStringArray(_dictionary);
		String[] _words = _sentence.split(" ");
		for(int i = 0; i < _words.length; i++)
		{
			for(String _badWord : _dictionary)
			{
				if(_words[i].equalsIgnoreCase(_badWord))
				{
					_words[i] = "";
					for(int j = 0; j < _badWord.length(); j++)
					{
						_words[i] += "*";
					}
				}
			}
		}
		return ArrayToString(_words);
	}

	private static void PrintStringArray(String[] _array)
	{
		for(String _line : _array)
		{
			System.out.println(_line);
		}
	}

	private static String ArrayToString(String[] _words)
	{
		String _line = "";
		for(String _word : _words)
		{
			_line += _word + " ";
		}
		_line = _line.substring(0, _line.length() - 1);
		return _line;
	}

}
