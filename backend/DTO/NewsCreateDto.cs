﻿namespace backend.DTO
{
    public class NewsCreateDto
    {
        public string Title { get; set; } = null!;
        public string Content { get; set; } = null!;
        public int? AuthorId { get; set; }
    }
}
