using backend.DTO;
using backend.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NewsController : ControllerBase
    {
        private readonly PRM392_News_ApplicationContext _context;

        public NewsController(PRM392_News_ApplicationContext context)
        {
            _context = context;
        }

        // GET: api/News
        [HttpGet]
        public async Task<ActionResult<IEnumerable<NewsDto>>> GetNews(NewsFilterDTO newsFilterDto)
        {
            return await _context.News
                .Select(news => new NewsDto
                {
                    NewsId = news.NewsId,
                    Title = news.Title,
                    Content = news.Content,
                    AuthorId = news.AuthorId,
                    PublishedAt = news.PublishedAt,
                    UpdatedAt = news.UpdatedAt
                })
                .ToListAsync();
        }

        // GET: api/News/5
        [HttpGet("{id}")]
        public async Task<ActionResult<NewsDto>> GetNews(int id)
        {
            var news = await _context.News.FindAsync(id);

            if (news == null)
            {
                return NotFound();
            }

            return new NewsDto
            {
                NewsId = news.NewsId,
                Title = news.Title,
                Content = news.Content,
                AuthorId = news.AuthorId,
                PublishedAt = news.PublishedAt,
                UpdatedAt = news.UpdatedAt
            };
        }

        // POST: api/News
        [HttpPost]
        public async Task<ActionResult<NewsDto>> PostNews(NewsCreateDto newsCreateDto)
        {
            var news = new News
            {
                Title = newsCreateDto.Title,
                Content = newsCreateDto.Content,
                AuthorId = newsCreateDto.AuthorId,
                PublishedAt = DateTime.UtcNow,
                UpdatedAt = DateTime.UtcNow
            };

            _context.News.Add(news);
            await _context.SaveChangesAsync();

            var newsDto = new NewsDto
            {
                NewsId = news.NewsId,
                Title = news.Title,
                Content = news.Content,
                AuthorId = news.AuthorId,
                PublishedAt = news.PublishedAt,
                UpdatedAt = news.UpdatedAt
            };

            return CreatedAtAction(nameof(GetNews), new { id = news.NewsId }, newsDto);
        }

        // PUT: api/News/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutNews(int id, NewsUpdateDto newsUpdateDto)
        {
            var news = await _context.News.FindAsync(id);

            if (news == null)
            {
                return NotFound();
            }

            news.Title = newsUpdateDto.Title;
            news.Content = newsUpdateDto.Content;
            news.AuthorId = newsUpdateDto.AuthorId;
            news.UpdatedAt = DateTime.UtcNow;

            _context.Entry(news).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!NewsExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // DELETE: api/News/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteNews(int id)
        {
            var news = await _context.News.FindAsync(id);

            if (news == null)
            {
                return NotFound();
            }

            _context.News.Remove(news);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool NewsExists(int id)
        {
            return _context.News.Any(e => e.NewsId == id);
        }
    }
}
