package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Complaint;

@Component
@Transactional
public class ComplaintToStringConverter implements Converter<Complaint, String>{
	@Override
	public String convert(Complaint source) {
		String result;
		
		if(source==null) 
			result=null;
		else 
			result=String.valueOf(source.getId());
		
		return result;
	}
}
