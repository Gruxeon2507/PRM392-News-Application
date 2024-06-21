using System;
using System.Collections.Generic;

namespace backend.Models
{
    public partial class UserProfile
    {
        public int ProfileId { get; set; }
        public int? UserId { get; set; }
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? Bio { get; set; }
        public string? AvatarUrl { get; set; }

        public virtual User? User { get; set; }
    }
}
