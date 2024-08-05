using backend.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoryController : ControllerBase
    {
        private readonly PRM392_News_ApplicationContext _context;

        public CategoryController(PRM392_News_ApplicationContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<IActionResult> listCategory()
        {
            List<NewsFilter> filters = _context.NewsFilters.ToList();
            return Ok(filters);
        }
    }
}
