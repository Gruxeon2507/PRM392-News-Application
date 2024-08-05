using backend.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CommentController : ControllerBase
    {
        private readonly PRM392_News_ApplicationContext _context;

        public CommentController(PRM392_News_ApplicationContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<IActionResult> listComment()
        {
            List<Comment> filters = _context.Comments.ToList();
            return Ok(filters);
        }
    }
}
