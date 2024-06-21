using System;
using System.Collections.Generic;

namespace backend.Models
{
    public partial class User
    {
        public User()
        {
            Comments = new HashSet<Comment>();
            News = new HashSet<News>();
            Statistics = new HashSet<Statistic>();
            UserProfiles = new HashSet<UserProfile>();
        }

        public int UserId { get; set; }
        public string Username { get; set; } = null!;
        public string PasswordHash { get; set; } = null!;
        public string Email { get; set; } = null!;
        public int? RoleId { get; set; }
        public DateTime? CreatedAt { get; set; }
        public DateTime? UpdatedAt { get; set; }

        public virtual UserRole? Role { get; set; }
        public virtual ICollection<Comment> Comments { get; set; }
        public virtual ICollection<News> News { get; set; }
        public virtual ICollection<Statistic> Statistics { get; set; }
        public virtual ICollection<UserProfile> UserProfiles { get; set; }
    }
}
