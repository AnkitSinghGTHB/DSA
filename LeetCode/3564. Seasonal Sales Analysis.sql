with categorized_sales as (
    select 
        p.category,
        case 
            when month(s.sale_date) in (12, 1, 2) then 'Winter'
            when month(s.sale_date) in (3, 4, 5) then 'Spring'
            when month(s.sale_date) in (6, 7, 8) then 'Summer'
            else 'Fall'
        end as season,
        s.quantity,
        s.quantity * s.price as revenue
    from sales s
    join products p on s.product_id = p.product_id
),
seasonal_summary as (
    select 
        season,
        category,
        sum(quantity) as total_quantity,
        sum(revenue) as total_revenue
    from categorized_sales
    group by season, category
),
ranked_categories as (
    select *,
           row_number() over (
               partition by season
               order by total_quantity desc, total_revenue desc
           ) as rank_num 
    from seasonal_summary
)
select 
    season,
    category,
    total_quantity,
    total_revenue
from ranked_categories
where rank_num = 1
order by 
    field(season,'Fall', 'Spring', 'Summer','Winter'); --lol we can comment using this, nvm i forgot that i know sqli already
