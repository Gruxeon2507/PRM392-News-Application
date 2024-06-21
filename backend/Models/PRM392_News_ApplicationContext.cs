using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace backend.Models
{
    public partial class PRM392_News_ApplicationContext : DbContext
    {
        public PRM392_News_ApplicationContext()
        {
        }

        public PRM392_News_ApplicationContext(DbContextOptions<PRM392_News_ApplicationContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Comment> Comments { get; set; } = null!;
        public virtual DbSet<News> News { get; set; } = null!;
        public virtual DbSet<NewsFilter> NewsFilters { get; set; } = null!;
        public virtual DbSet<Statistic> Statistics { get; set; } = null!;
        public virtual DbSet<User> Users { get; set; } = null!;
        public virtual DbSet<UserProfile> UserProfiles { get; set; } = null!;
        public virtual DbSet<UserRole> UserRoles { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see http://go.microsoft.com/fwlink/?LinkId=723263.
                optionsBuilder.UseSqlServer("server =(local); database =PRM392_News_Application;uid=duckm;pwd=123456;Trusted_Connection=True;Encrypt=False");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Comment>(entity =>
            {
                entity.Property(e => e.CommentId).HasColumnName("comment_id");

                entity.Property(e => e.Content).HasColumnName("content");

                entity.Property(e => e.CreatedAt)
                    .HasColumnType("datetime")
                    .HasColumnName("created_at")
                    .HasDefaultValueSql("(getdate())");

                entity.Property(e => e.NewsId).HasColumnName("news_id");

                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.HasOne(d => d.News)
                    .WithMany(p => p.Comments)
                    .HasForeignKey(d => d.NewsId)
                    .HasConstraintName("FK__Comments__news_i__33D4B598");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.Comments)
                    .HasForeignKey(d => d.UserId)
                    .HasConstraintName("FK__Comments__user_i__32E0915F");
            });

            modelBuilder.Entity<News>(entity =>
            {
                entity.Property(e => e.NewsId).HasColumnName("news_id");

                entity.Property(e => e.AuthorId).HasColumnName("author_id");

                entity.Property(e => e.Content).HasColumnName("content");

                entity.Property(e => e.PublishedAt)
                    .HasColumnType("datetime")
                    .HasColumnName("published_at")
                    .HasDefaultValueSql("(getdate())");

                entity.Property(e => e.Title)
                    .HasMaxLength(255)
                    .HasColumnName("title");

                entity.Property(e => e.UpdatedAt)
                    .HasColumnType("datetime")
                    .HasColumnName("updated_at")
                    .HasDefaultValueSql("(getdate())");

                entity.HasOne(d => d.Author)
                    .WithMany(p => p.News)
                    .HasForeignKey(d => d.AuthorId)
                    .HasConstraintName("FK__News__author_id__2F10007B");
            });

            modelBuilder.Entity<NewsFilter>(entity =>
            {
                entity.HasKey(e => e.FilterId)
                    .HasName("PK__NewsFilt__833C443F7CA9EAE6");

                entity.Property(e => e.FilterId).HasColumnName("filter_id");

                entity.Property(e => e.FilterName)
                    .HasMaxLength(100)
                    .HasColumnName("filter_name");

                entity.Property(e => e.FilterValue)
                    .HasMaxLength(100)
                    .HasColumnName("filter_value");
            });

            modelBuilder.Entity<Statistic>(entity =>
            {
                entity.HasKey(e => e.StatId)
                    .HasName("PK__Statisti__B8A52560A8A11596");

                entity.Property(e => e.StatId).HasColumnName("stat_id");

                entity.Property(e => e.CommentCount)
                    .HasColumnName("comment_count")
                    .HasDefaultValueSql("((0))");

                entity.Property(e => e.LikeCount)
                    .HasColumnName("like_count")
                    .HasDefaultValueSql("((0))");

                entity.Property(e => e.NewsId).HasColumnName("news_id");

                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.Property(e => e.ViewCount)
                    .HasColumnName("view_count")
                    .HasDefaultValueSql("((0))");

                entity.HasOne(d => d.News)
                    .WithMany(p => p.Statistics)
                    .HasForeignKey(d => d.NewsId)
                    .HasConstraintName("FK__Statistic__news___440B1D61");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.Statistics)
                    .HasForeignKey(d => d.UserId)
                    .HasConstraintName("FK__Statistic__user___4316F928");
            });

            modelBuilder.Entity<User>(entity =>
            {
                entity.HasIndex(e => e.Email, "UQ__Users__AB6E6164C7C0CDA6")
                    .IsUnique();

                entity.HasIndex(e => e.Username, "UQ__Users__F3DBC5728FA56562")
                    .IsUnique();

                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.Property(e => e.CreatedAt)
                    .HasColumnType("datetime")
                    .HasColumnName("created_at")
                    .HasDefaultValueSql("(getdate())");

                entity.Property(e => e.Email)
                    .HasMaxLength(100)
                    .HasColumnName("email");

                entity.Property(e => e.PasswordHash)
                    .HasMaxLength(255)
                    .HasColumnName("password_hash");

                entity.Property(e => e.RoleId).HasColumnName("role_id");

                entity.Property(e => e.UpdatedAt)
                    .HasColumnType("datetime")
                    .HasColumnName("updated_at")
                    .HasDefaultValueSql("(getdate())");

                entity.Property(e => e.Username)
                    .HasMaxLength(50)
                    .HasColumnName("username");

                entity.HasOne(d => d.Role)
                    .WithMany(p => p.Users)
                    .HasForeignKey(d => d.RoleId)
                    .HasConstraintName("FK_Role");
            });

            modelBuilder.Entity<UserProfile>(entity =>
            {
                entity.HasKey(e => e.ProfileId)
                    .HasName("PK__UserProf__AEBB701F7F301A2D");

                entity.Property(e => e.ProfileId).HasColumnName("profile_id");

                entity.Property(e => e.AvatarUrl)
                    .HasMaxLength(255)
                    .HasColumnName("avatar_url");

                entity.Property(e => e.Bio).HasColumnName("bio");

                entity.Property(e => e.FirstName)
                    .HasMaxLength(50)
                    .HasColumnName("first_name");

                entity.Property(e => e.LastName)
                    .HasMaxLength(50)
                    .HasColumnName("last_name");

                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserProfiles)
                    .HasForeignKey(d => d.UserId)
                    .HasConstraintName("FK__UserProfi__user___38996AB5");
            });

            modelBuilder.Entity<UserRole>(entity =>
            {
                entity.HasKey(e => e.RoleId)
                    .HasName("PK__UserRole__760965CCAD93E9F8");

                entity.HasIndex(e => e.RoleName, "UQ__UserRole__783254B1F358C9A3")
                    .IsUnique();

                entity.Property(e => e.RoleId).HasColumnName("role_id");

                entity.Property(e => e.RoleName)
                    .HasMaxLength(50)
                    .HasColumnName("role_name");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
