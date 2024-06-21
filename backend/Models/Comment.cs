using System;
using System.Collections.Generic;

namespace backend.Models
{
    public partial class Comment
    {
        public int CommentId { get; set; }
        public int? UserId { get; set; }
        public int? NewsId { get; set; }
        public string Content { get; set; } = null!;
        public DateTime? CreatedAt { get; set; }

        public virtual News? News { get; set; }
        public virtual User? User { get; set; }
    }
}
