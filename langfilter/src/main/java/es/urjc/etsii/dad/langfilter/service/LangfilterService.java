package es.urjc.etsii.dad.langfilter.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LangfilterService {

	private String[] dictionary = fileToString("langfilter/src/resources/Dictionary.txt").split("\n");

    private String fileToString(String _path)
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
