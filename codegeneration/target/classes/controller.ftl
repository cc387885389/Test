
@SuppressWarnings("all")
@RestController
@RequestMapping("/api/${minClassName}")
public class ${className} extends BaseController {
	
	@Autowired
	private I${name}Service ${name?uncap_first}Service;
	
	
	@RequestMapping()
	public Object list(@RequestParam(required = false) String draw, @RequestParam(defaultValue = "1") int start, 
			@RequestParam(defaultValue = "20") int length, @RequestParam(defaultValue = "id") String orderColumn,
			@RequestParam(defaultValue = "DESC") String orderSort, @RequestParam(required = false) String code) {

		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(code)) {
			params.put("code", code.trim());
		}
		PageRequest pageRequest = new PageRequest(start, length, new Sort(Sort.Direction.valueOf(orderSort.toUpperCase()), orderColumn));
		Page<${name}> page = ${name?uncap_first}Service.searchScrollPage(params, pageRequest);

		return new PageTable<${name}>(page.getContent(), draw, page.getContent().size());
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(Constants.YYYYMMDD), true));
		binder.registerCustomEditor(String.class, new StringEscapeEditor());
	}
}