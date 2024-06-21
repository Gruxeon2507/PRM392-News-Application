using System;
using System.Collections.Generic;

namespace backend.Models
{
    public partial class NewsFilter
    {
        public int FilterId { get; set; }
        public string FilterName { get; set; } = null!;
        public string FilterValue { get; set; } = null!;
    }
}
