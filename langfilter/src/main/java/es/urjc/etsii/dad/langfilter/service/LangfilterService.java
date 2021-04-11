package es.urjc.etsii.dad.langfilter.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class LangfilterService {

	private boolean deployed = false;
	private String[] dictionary = fileToString("langfilter/src/main/resources/dictionary.txt").split("\n");

    private String fileToString(String _path)
	{
		try
		{
			BufferedReader br;
			if (deployed){
				Resource resource = new ClassPathResource("classpath:dictionary.txt");
				InputStream is = resource.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
			}
			else{
				FileReader fr = new FileReader(_path);
				br = new BufferedReader(fr);
			}			
			
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

	public String filter(String _sentence)
	{
		//PrintStringArray(_dictionary);
		String[] _words = _sentence.split(" ");
		for(int i = 0; i < _words.length; i++)
		{
			for(String _badWord : dictionary)
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

	private void PrintStringArray(String[] _array)
	{
		for(String _line : _array)
		{
			System.out.println(_line);
		}
	}

	private String ArrayToString(String[] _words)
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
