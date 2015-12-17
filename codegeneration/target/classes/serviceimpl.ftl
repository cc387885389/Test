

@Service
public class ${className} extends CrudService<${name}Dao, ${name}> implements I${name}Service {

	@Autowired
	private ${name}Dao ${name?uncap_first}Dao;
	
}