namespace backend.DTO
{
    public class NewsUpdateDto
    {
        public string Title { get; set; } = null!;
        public string Content { get; set; } = null!;
        public int? AuthorId { get; set; }
    }
}
