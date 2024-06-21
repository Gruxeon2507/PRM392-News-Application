using System;
using System.Collections.Generic;

namespace backend.Models
{
    public partial class Statistic
    {
        public int StatId { get; set; }
        public int? UserId { get; set; }
        public int? NewsId { get; set; }
        public int? ViewCount { get; set; }
        public int? LikeCount { get; set; }
        public int? CommentCount { get; set; }

        public virtual News? News { get; set; }
        public virtual User? User { get; set; }
    }
}
