using System;
using System.Collections.Generic;

namespace backend.Models
{
    public partial class News
    {
        public News()
        {
            Comments = new HashSet<Comment>();
            Statistics = new HashSet<Statistic>();
        }

        public int NewsId { get; set; }
        public string Title { get; set; } = null!;
        public string Content { get; set; } = null!;
        public int? AuthorId { get; set; }
        public DateTime? PublishedAt { get; set; }
        public DateTime? UpdatedAt { get; set; }

        public virtual User? Author { get; set; }
        public virtual ICollection<Comment> Comments { get; set; }
        public virtual ICollection<Statistic> Statistics { get; set; }
    }
}
